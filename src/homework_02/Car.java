package homework_02;

import java.util.ArrayList;
import java.util.List;

public class Car {

	private String name;
	private List<Tire> tires;
	private List<Engine> engine;
	private List<Seats> seats;
	private List<Frame> frame;

	Car(String name) {
		this.name = name;
		this.engine = new ArrayList<>();
		this.seats = new ArrayList<>();
		this.frame = new ArrayList<>();
		this.tires = new ArrayList<>();
	}

	private class Tire implements Runnable {

		private String name;

		Tire(String guma) {
			this.name = guma;
			tires.add(this);
			System.out.println("New tire was made. It took 2 seconds.");
		}

		@Override
		public void run() {

			for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(2000);
					new Tire("Guma");
				} catch (InterruptedException e) {
					System.out.println("Someone wanted me to stop. Let it be!!!");
					return;
				}
			}
		}

		@Override
		public String toString() {
			return "Tire [name=" + name + "]";
		}

	}

	private class Engine implements Runnable {

		private String name;

		Engine(String dvigatel) {
			this.name = dvigatel;
			engine.add(this);
			System.out.println("New engine was made. It took 7 seconds.");
		}

		@Override
		public void run() {

			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				System.out.println("Someone wanted me to stop. Let it be!!!");
				return;
			}
		}

		@Override
		public String toString() {
			return "Engine [name=" + name + "]";
		}

	}

	private class Seats implements Runnable {

		private String name;

		Seats(String kreslo) {
			this.name = kreslo;
			seats.add(this);
			System.out.println("New seat was made. It took 3 seconds.");
		}

		@Override
		public void run() {
			for (int i = 0; i < 4; i++) {
				try {
					Thread.sleep(3000);
					new Seats("Kreslo");
				} catch (InterruptedException e) {
					System.out.println("Someone wanted me to stop. Let it be!!!");
					return;
				}
			}
		}

		@Override
		public String toString() {
			return "Seats [name=" + name + "]";
		}

	}

	private class Frame implements Runnable {

		private String name;

		Frame(String rama) {
			this.name = rama;
			frame.add(this);
			System.out.println("New frame was made. It took 2 seconds.");
		}

		@Override
		public void run() {

			try {
				Thread.sleep(5000);

			} catch (InterruptedException e) {
				System.out.println("Someone wanted me to stop. Let it be!!!");
				return;
			}
		}

		@Override
		public String toString() {
			return "Frame [name=" + name + "]";
		}

	}

	public static void main(String[] args) throws InterruptedException {

		Car car = new Car("Audi");
		long start = System.currentTimeMillis();

		Thread seats = new Thread(car.new Seats("Kreslo"));
		Thread tire = new Thread(car.new Tire("Guma"));
		Thread engine = new Thread(car.new Engine("Dvigatel"));
	
		seats.start();
		engine.start();
		tire.start();
		
		engine.join();
		seats.join();
		tire.join();
		
		Thread frame = null;
		while (!seats.isAlive() || !engine.isAlive() || !tire.isAlive()) {
			frame = new Thread(car.new Frame("Rama"));
			frame.start();
			break;
		}

	    frame.join();
		
		long timeTaken = (System.currentTimeMillis() - start) / 1000;
		System.out
				.println("The car " + car.name + ".It took " + timeTaken + " seconds to make components for the car.");

		System.out.println("Components of the car:");
		System.out.println(car.tires);
		System.out.println(car.engine);
		System.out.println(car.seats);
		System.out.println(car.frame);

	}
}
