/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Tetris Project (c) 2012 - 2011 by Hans Ferchland & Hady Khalifa
 * 
 * 
 * Tetris is a tetris clone in java using the JIT Framework
 * 
 * 
 * Tetris rights are by its owners/creators (Hans Ferchland & Hady Khalifa). 
 * You have no right to edit, publish and/or deliver the code or application in any way! If that is done by someone, please report it!
 * 
 * Email us: hans.ferchland@gmx.de
 * 
 * Project: Tetris
 * File: GameStepper.java
 * Type: logic.GameStepper
 * 
 * Documentation created: 17.01.2012 - 20:03:32 by Hans
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

import objects.BaseObject;

import framework.core.Application;
import framework.core.TimedEvent;
import framework.events.KeyboardControl;
import framework.events.TimedControl;

/**
 * The Class GameStepper.
 */
public class GameStepper implements TimedControl {

	/** The instance. */
	private static GameStepper instance;
	
	private static CurrentBlockStepper blockStepper;

	private long period = 800;

	private ArrayList<BaseObject> gameObjects;

	private BaseObject currentMainBlock;

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
		gameObjects = new ArrayList<BaseObject>();
		blockStepper = new CurrentBlockStepper();
	}

	public void setCurrentBlock(BaseObject block) {
		currentMainBlock = block;
	}

	/**
	 * Adds a game object.
	 * 
	 * @param obj
	 *            the obj
	 */
	public void addGameObject(BaseObject obj) {
		gameObjects.add(obj);
	}

	/**
	 * Removes a game object.
	 * 
	 * @param obj
	 *            the obj
	 */
	public void removeGameObject(BaseObject obj) {
		gameObjects.remove(obj);
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

		if (p.y < 26)
			currentMainBlock.setPosition(p.x, p.y + 1);

	}

	/**
	 * Removes the lines.
	 * 
	 * TODO: This method needs rework! All blocks that are out of screen have to be deleted!
	 *
	 * @param value the value
	 */
	public void removeLines(int value) {
		for (BaseObject o : gameObjects) {

			Point p = o.getPosition();

			if (p.y < 26)
				o.setPosition(p.x, p.y + value);
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
	 * The Class CurrentBlockStepper.
	 */
	class CurrentBlockStepper implements TimedControl, KeyboardControl {
		
		private Queue<KeyEvent> keyEvents;

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
		
		@Override
		public void keyPressed(KeyEvent event) {

			
		}

		@Override
		public void keyReleased(KeyEvent event) {
			Point p = currentMainBlock.getPosition();
			// arrow left is pressed
			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				if (p.x > 0) keyEvents.add(event);
			}
			// arrow right is pressed
			else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (p.x < 10) keyEvents.add(event);
			}
			// arrow down is pressed
			else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				if (p.y > 26) keyEvents.add(event);
			}
		}

		@Override
		public void keyTyped(KeyEvent event) {
			
		}

		@Override
		public void onTimedEvent(TimedEvent t) {
			if (!keyEvents.isEmpty()) {
				KeyEvent e = keyEvents.poll();
				Point p = currentMainBlock.getPosition();
				// arrow left is pressed
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (p.x > 0) currentMainBlock.setPosition(p.x - 1, p.y);
				}
				// arrow right is pressed
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (p.x < 10) currentMainBlock.setPosition(p.x + 1, p.y);
				}
				// arrow down is pressed
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (p.y > 26) currentMainBlock.setPosition(p.x, p.y + 1);
				}
			}
		}

		@Override
		public long getPeriod() {
			return 200;
		}
	}

}