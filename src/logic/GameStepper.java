/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Tetris Project (c) 2011 - 2012 by Hans Ferchland & Hady Khalifa
 * 
 * 
 * Tetris is a tetris clone in java using the JIT Framework
 * 
 * 
 * Tetris rights are by its owners/creators 
 * (Hans Ferchland & Hady Khalifa). You have no right to 
 * publish and/or deliver the code or application in any way!
 * 
 * If that is done by someone, please report it!
 * 
 * Email us: hans.ferchland@gmx.de
 * 
 * Project: Tetris
 * File: GameStepper.java
 * Type: logic.GameStepper
 * 
 * Documentation created: 18.01.2012 - 23:08:28 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import core.TetrisGame;

import objects.BaseObject;
import objects.BlockType;
import framework.core.Application;
import framework.core.TimedEvent;
import framework.events.KeyboardControl;
import framework.events.TimedControl;
import framework.objects.Text;

/**
 * The Class GameStepper.
 */
public class GameStepper implements TimedControl {

	static final int STANDARD_PERIOD = 200;

	/** The instance. */
	private static GameStepper instance;

	/** The block stepper. */
	private static CurrentBlockStepper blockStepper;

	/** The period. */
	private long period = STANDARD_PERIOD;

	/** The current main block. */
	private BaseObject currentMainBlock;

	/** The next main block. */
	private BaseObject nextMainBlock;

	/** The collision. */
	private FieldCollision collision;

	/** The inactive blocks. */
	private ArrayList<BaseObject> inactiveBlocks;
	
	private int blockPointsTime = 0;

	/**
	 * Gets the single instance of GameStepper.
	 * 
	 * @return single instance of GameStepper
	 */
	public static GameStepper getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new GameStepper();
	}

	/**
	 * Instantiates a new game stepper.
	 */
	private GameStepper() {
		Application.getInstance().addTimedObject(this);
		inactiveBlocks = new ArrayList<BaseObject>();
		blockStepper = new CurrentBlockStepper();
		collision = FieldCollision.getInstance();
	}

	/**
	 * Start.
	 */
	public void start() {
		generateNextBlock();
		setCurrentBlock(nextMainBlock);
		generateNextBlock();

	}

	/**
	 * Sets the current block.
	 * 
	 * @param block
	 *            the new current block
	 */
	private void setCurrentBlock(BaseObject block) {
		inactiveBlocks.add(currentMainBlock);
		currentMainBlock = block;
		nextMainBlock.setPosition(5, 0);
		GameScore.getInstance().setTimeBlockCreated(blockPointsTime = 0);
	}

	/**
	 * Generate next block.
	 */
	private void generateNextBlock() {
		Random r = new Random(System.currentTimeMillis());
		int random = Math.abs(r.nextInt()) % 7; // TODO: Change 2 into 7, this
												// is the number of block-types

		switch (random) {
		case 0:
			nextMainBlock = new objects.Square();
			break;
		case 1:
			nextMainBlock = new objects.Long();
			break;
		case 2:
			nextMainBlock = new objects.Pyramid();
			break;
		case 3:
			nextMainBlock = new objects.StairL();
			break;
		case 4:
			nextMainBlock = new objects.StairR();
			break;
		case 5:
			nextMainBlock = new objects.AngleL();
			break;
		case 6:
			nextMainBlock = new objects.AngleR();
			break;
		default:
			nextMainBlock = new objects.Square();
			break;
		}
		nextMainBlock.setPosition(14, 8);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * framework.events.TimedControl#onTimedEvent(framework.core.TimedEvent)
	 */
	@Override
	public void onTimedEvent(TimedEvent t) {

		Point p = currentMainBlock.getPosition();
		checkMainBlockCollisionVertical(new Point(p.x, p.y + 1));
		blockPointsTime++;
	}

	/**
	 * This method is called if a block gets unmovable or inactive.
	 */
	private void onBlockInactive() {
		// add current block to inactives
		collision.addInactiveBlock(currentMainBlock);
		// calculate score if block get inactive
		GameScore.getInstance()
				.setTimeBlockInactive(blockPointsTime);
		
		
		if (!checkGameOver()) {
			// get the next block and set that as the current one
			setCurrentBlock(nextMainBlock);
			// generate preview of next block
			generateNextBlock();
			// check if a line is filled
			checkLines();
		}
	}

	/**
	 * Checks lines and sets all blocks on the new position.
	 */
	private void checkLines() {
		GameScore.getInstance().linesRemoved(collision.checkLines());
	}

	/**
	 * Check main block collision.
	 * 
	 * @param desired
	 *            the desired
	 */
	private void checkMainBlockCollisionVertical(Point desired) {
		if (currentMainBlock.checkCollision(collision, desired) == 1) {
			// a block is at the bottom or on some other brick
			onBlockInactive();
		} else {
			// the block can move to the next y-pos
			currentMainBlock.setPosition(desired.x, desired.y);
		}
	}

	/**
	 * Check main block collision by rotate.
	 * 
	 * @param desired
	 *            the desired
	 */
	private void checkMainBlockCollisionRotate(Point desired) {
		int checkValue = currentMainBlock.checkCollisionRotate(collision,
				desired);
		if (checkValue == 1 || checkValue == 2) {

		} else {
			currentMainBlock.rotate();

		}
	}

	/**
	 * Check main block collision horizontal.
	 * 
	 * @param desired
	 *            the desired
	 */
	private void checkMainBlockCollisionHorizontal(Point desired) {
		int checkValue = currentMainBlock.checkCollision(collision, desired);
		if (checkValue == 1 || checkValue == 2) {

		} else {
			currentMainBlock.setPosition(desired.x, desired.y);
		}
	}

	/**
	 * Checks if game is over and ends game in case of.
	 * 
	 * @return true, if game is over
	 */
	private boolean checkGameOver() {
		if (collision.isGameOver()) {
			Application.getInstance().removeTimedObject(this);
			Application.getInstance().removeTimedObject(blockStepper);
			Application.getInstance().removeKeyboardControl(blockStepper);
			collision.resetField();
			for (BaseObject o : inactiveBlocks) {
				if (o != null) {
					o.dispose();
				}
			}
			inactiveBlocks.clear();
			currentMainBlock.dispose();
			currentMainBlock = null;
			nextMainBlock.dispose();
			nextMainBlock = null;
			period = STANDARD_PERIOD;
			blockStepper.keyEvents.clear();
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.events.TimedControl#getPeriod()
	 */
	@Override
	public long getPeriod() {
		return period;
	}

	public void pause() {
		//Application.getInstance().pause();
		Application.getInstance().removeKeyboardControl(blockStepper);
		Application.getInstance().removeTimedObject(blockStepper);
		Application.getInstance().removeTimedObject(this);
		
		if (TetrisGame.pauseLabel == null)
			TetrisGame.pauseLabel = new Text(6 * 20, 15 * 20, "PAUSE", new Font("Tahoma", Font.BOLD, 26), Color.BLACK);
		TetrisGame.pauseLabel.makeVisible();
		
		Application.getInstance().addKeyboardControl(new KeyboardControl() {
			
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_P) {
					TetrisGame.pauseLabel.makeInvisible();
					Application.getInstance().removeKeyboardControl(this);
					addControls();
				//Application.getInstance().resume();
				}
			}

			@Override
			public void keyReleased(KeyEvent event) {

			}

			@Override
			public void keyTyped(KeyEvent event) {

			}
		});
	}

	private void addControls() {
		Application.getInstance().addTimedObject(blockStepper);
		Application.getInstance().addKeyboardControl(blockStepper);
		Application.getInstance().addTimedObject(this);
	}

	/**
	 * The Class CurrentBlockStepper.
	 */
	class CurrentBlockStepper implements TimedControl, KeyboardControl {

		/** The key events. */
		private Queue<KeyEvent> keyEvents;

		/**
		 * Instantiates a new current block stepper.
		 */
		public CurrentBlockStepper() {
			Application.getInstance().addTimedObject(this);
			Application.getInstance().addKeyboardControl(this);
			keyEvents = new Queue<KeyEvent>() {

				LinkedList<KeyEvent> elements = new LinkedList<KeyEvent>();

				@Override
				public <T> T[] toArray(T[] array) {
					return elements.toArray(array);
				}

				@Override
				public Object[] toArray() {
					return elements.toArray();
				}

				@Override
				public int size() {
					return elements.size();
				}

				@Override
				public boolean retainAll(Collection<?> arg0) {
					return elements.retainAll(arg0);
				}

				@Override
				public boolean removeAll(Collection<?> arg0) {
					return elements.removeAll(arg0);
				}

				@Override
				public boolean remove(Object object) {
					return false;
				}

				@Override
				public Iterator<KeyEvent> iterator() {
					return elements.iterator();
				}

				@Override
				public boolean isEmpty() {
					return elements.isEmpty();
				}

				@Override
				public boolean containsAll(Collection<?> arg0) {

					return elements.containsAll(arg0);
				}

				@Override
				public boolean contains(Object object) {
					return elements.contains(object);
				}

				@Override
				public void clear() {
					elements.clear();

				}

				@Override
				public boolean addAll(Collection<? extends KeyEvent> arg0) {
					return elements.addAll(arg0);
				}

				@Override
				public boolean add(KeyEvent object) {
					elements.addLast(object);
					return true;
				}

				@Override
				public KeyEvent remove() {
					return null;
				}

				@Override
				public KeyEvent poll() {
					if (isEmpty())
						return null;
					KeyEvent t = elements.getFirst();
					try {
						elements.removeFirst();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return t;
				}

				@Override
				public KeyEvent peek() {
					KeyEvent t = null;
					try {
						t = elements.getFirst();
					} finally {
						if (t != null)
							elements.remove(t);
					}
					return t;
				}

				@Override
				public boolean offer(KeyEvent e) {
					return false;
				}

				@Override
				public KeyEvent element() {
					return null;
				}
			};
		}

		private void move(KeyEvent event) {
			// arrow left is pressed
			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				keyEvents.add(event);
			}
			// arrow right is pressed
			if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				keyEvents.add(event);
			}
			// arrow down is pressed
			if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				keyEvents.add(event);
			}
			// arrow up is pressed
			else if (event.getKeyCode() == KeyEvent.VK_UP) {
				keyEvents.add(event);
			}
			// "P" is pressed
			else if (event.getKeyCode() == KeyEvent.VK_P) {
				keyEvents.clear();
				keyEvents.add(event);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * framework.events.KeyboardControl#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent event) {
			move(event);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * framework.events.KeyboardControl#keyReleased(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyReleased(KeyEvent event) {
			keyEvents.clear();
			// move(event);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * framework.events.KeyboardControl#keyTyped(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyTyped(KeyEvent event) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * framework.events.TimedControl#onTimedEvent(framework.core.TimedEvent)
		 */
		@Override
		public void onTimedEvent(TimedEvent t) {
			if (!keyEvents.isEmpty()) {
				KeyEvent e = keyEvents.poll();
				Point p = currentMainBlock.getPosition();
				// arrow left is pressed
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					checkMainBlockCollisionHorizontal(new Point(p.x - 1, p.y));
				}
				// arrow right is pressed
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					checkMainBlockCollisionHorizontal(new Point(p.x + 1, p.y));
				}
				// arrow down is pressed
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					checkMainBlockCollisionVertical(new Point(p.x, p.y + 1));
				}
				// arrow up is pressed
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					checkMainBlockCollisionRotate(new Point(p.x, p.y));
				}
				// "P" is pressed
				else if (e.getKeyCode() == KeyEvent.VK_P) {
					pause();
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see framework.events.TimedControl#getPeriod()
		 */
		@Override
		public long getPeriod() {
			return 50;
		}
	}

}
