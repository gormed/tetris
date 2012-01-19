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
 * Documentation created: 19.01.2012 - 18:54:41 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import objects.BaseObject;
import framework.core.Application;
import framework.core.TimedEvent;
import framework.events.KeyboardControl;
import framework.events.TimedControl;
import gui.GameOver;
import gui.Level;
import gui.Pause;
import gui.StartGame;

/**
 * The Class GameStepper.
 */
public class GameStepper implements TimedControl {

	/** The Constant STANDARD_PERIOD. */
	static final int STANDARD_PERIOD = 800;
	
	/** The Constant GAME_LEVEL for the timed event period, this is the game-speed. */
	static final int[] GAME_LEVEL = { 800, 700, 600, 500, 400, 300, 200, 150, 100, 50 };
	
	/** The Constant MAX_LEVEL. */
	static final int MAX_LEVEL = 9;
	
	/** The Constant LINES_FOR_NEXT_LEVEL indicates the lines to be removed for the next level. */
	static final int[] LINES_FOR_NEXT_LEVEL = { 5, 10, 20, 35, 50, 65, 80, 100 };

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

	/** The block points time to calculate score. */
	private int blockPointsTime = 0;

	/** The game over gui. */
	private GameOver gameOverGUI;

	/** The pause gui. */
	private Pause pauseGUI;
	
	/** The start game gui. */
	private StartGame startGameGUI;

	/** The game over flag. */
	private boolean gameOverFlag = true;
	
	/** The game paused flag. */
	@SuppressWarnings("unused")
	private boolean gamePausedFlag = false;
	
	/** The current level. */
	private int currentLevel = 0;
	
	/** The level label. */
	private Level levelLabel;

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
		
		inactiveBlocks = new ArrayList<BaseObject>();
		blockStepper = new CurrentBlockStepper();
		collision = FieldCollision.getInstance();

		pauseGUI = new Pause();
		gameOverGUI = new GameOver();
		startGameGUI = new StartGame();
	}

	/**
	 * Start.
	 */
	public void start() {
		GameScore.getInstance().resetGameScore();
		generateNextBlock();
		setCurrentBlock(nextMainBlock);
		generateNextBlock();
		gameOverFlag = false;
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
		int random = Math.abs(r.nextInt()) % 7;

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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * framework.events.TimedControl#onTimedEvent(framework.core.TimedEvent)
	 */
	@Override
	public void onTimedEvent(TimedEvent t) {
		if (!gameOverFlag) {
			Point p = currentMainBlock.getPosition();
			checkMainBlockCollisionVertical(new Point(p.x, p.y + 1));
			blockPointsTime++;
		}
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
	
	/**
	 * Gets the current level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return currentLevel;
	}
	
	/**
	 * Sets the level period.
	 *
	 * @param level the new level period
	 */
	private void setLevelPeriod(int level) {
		period = GAME_LEVEL[currentLevel = level];
	}
	
	/**
	 * Checks the level lines.
	 *
	 * @param totalLines the total lines
	 */
	private void checkLevelLines(int totalLines) {
		if (currentLevel < MAX_LEVEL && totalLines >= LINES_FOR_NEXT_LEVEL[currentLevel]) {
			setLevelPeriod(++currentLevel);
			levelLabel.setLevel(currentLevel);
			MusicPlayer.getInstance().playSound(MusicPlayer.LEVELUP);
		}
	}
	
	/**
	 * Sets the level label.
	 *
	 * @param levelLabel the new level label
	 */
	public void setLevelLabel(Level levelLabel) {
		this.levelLabel = levelLabel;
	}
	
	/**
	 * This method is called if a block gets unmove-able or inactive.
	 */
	private void onBlockInactive() {
		// add current block to inactives
		collision.addInactiveBlock(currentMainBlock);
		// calculate score if block get inactive
		GameScore.getInstance().setTimeBlockInactive(blockPointsTime);

		if (!checkGameOver()) {
			// get the next block and set that as the current one
			setCurrentBlock(nextMainBlock);
			// generate preview of next block
			generateNextBlock();
			// check if a line is filled
			checkLines();
			// Level up?
			checkLevelLines(GameScore.getInstance().getTotalLinesRemoved());
		} else {
			gameOver();
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
			gameOverFlag = true;
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

	/**
	 * Game over gui.
	 */
	private void gameOver() {

		gameOverGUI.makeVisible();

		Application.getInstance().addKeyboardControl(new KeyboardControl() {

			@Override
			public void keyPressed(KeyEvent event) {
				// If enter is pressed, the game restarts
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					gameOverGUI.makeInvisible();
					//addControls();
					//start();
					startGame();
					Application.getInstance().removeKeyboardControl(this);
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
	
	/**
	 * Start game.
	 */
	public void startGame() {
		startGameGUI.makeVisible();
		
		
		Application.getInstance().addKeyboardControl(new KeyboardControl() {

			@Override
			public void keyPressed(KeyEvent event) {
				// If enter is pressed, the game restarts
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					startGameGUI.makeInvisible();
					levelLabel.setLevel(currentLevel);
					setLevelPeriod(currentLevel);
					addControls();
					start();
					Application.getInstance().removeKeyboardControl(this);
				}
				if (event.getKeyCode() == KeyEvent.VK_UP) {
					if (currentLevel < MAX_LEVEL)
						startGameGUI.changeDisplay(++currentLevel);
				}
				if (event.getKeyCode() == KeyEvent.VK_DOWN) {
					if (currentLevel > 0)
						startGameGUI.changeDisplay(--currentLevel);
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

	/**
	 * Pause.
	 */
	public void pause() {
		if (gameOverFlag)
			return;
		// Application.getInstance().pause();
		Application.getInstance().removeKeyboardControl(blockStepper);
		Application.getInstance().removeTimedObject(blockStepper);
		Application.getInstance().removeTimedObject(this);
		gamePausedFlag = true;
		pauseGUI.makeVisible();

		Application.getInstance().addKeyboardControl(new KeyboardControl() {

			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_P) {
					pauseGUI.makeInvisible();
					Application.getInstance().removeKeyboardControl(this);
					addControls();
					gamePausedFlag = false;
					// Application.getInstance().resume();
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

	/**
	 * Adds the controls.
	 */
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

		/**
		 * Move.
		 *
		 * @param event the event
		 */
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
		/**
		 * Key pressed.
		 *
		 * @param event the event
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
		/**
		 * Key released.
		 *
		 * @param event the event
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
		/**
		 * Key typed.
		 *
		 * @param event the event
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
		/**
		 * On timed event.
		 *
		 * @param t the t
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
		/**
		 * Gets the period.
		 *
		 * @return the period
		 */
		@Override
		public long getPeriod() {
			return 50;
		}
	}

}
