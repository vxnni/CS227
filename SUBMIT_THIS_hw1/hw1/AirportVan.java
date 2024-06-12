package hw1;
	/**
	 * This class contains all of the methods, variables, and constructor to compute a driver's total pay and average hourly pay
	 * @author Vincent Wang
	 */
public class AirportVan {
	/**
	 * Variable that stores the time spent driving in minutes
	 */
	private int minutesDriving;
	/**
	 * Variable stores the total amount of tips in dollars
	 */
	private double totalTip;
	/**
	 * Variable that stores the hourly pay rate of the driver
	 */
	private double hourlyRate;
	/**
	 * Variable stores the mileage bonus used to calculate bonus points for the driver
	 */
	private int mileageBonus;
	/**
	 * Variable stores the time bonus used to calculate bonus points for the driver
	 */
	private int timeBonus;
	/**
	 * Variable stores the number of max riders allowed on the van
	 */
	private int maxRiders;
	/**
	 * Variable stores the current number of riders in the van
	 */
	private int riders;
	/**
	 * Variable stores the total amount of time the driver has been working in minutes
	 */
	private int totalTime;
	/**
	 * Variable stores the total amount of miles the driver has driven
	 */
	private int totalMiles;
	/**
	 * Variable stores the total amount of time the driver has spent driving
	 */
	private int timeDriven;
	/**
	 * Constructs a new AirportVan with initially no riders and driver's wages, tips, and bonus points are set to zero
	 * @param givenHourlyRate Hourly pay rate for the driver
	 * @param givenMileageBonus Mileage bonus for amount of miles driven 
	 * @param givenTimeBonus Time bonus for the amount of time spent driving
	 * @param givenMaxRiders Max number of riders in the van
	 */
	public AirportVan(double givenHourlyRate, int givenMileageBonus, int givenTimeBonus, int givenMaxRiders) {
		maxRiders = givenMaxRiders;
		mileageBonus = givenMileageBonus;
		timeBonus = givenTimeBonus;
		hourlyRate = givenHourlyRate;
	}
	/**
	 * Simulates driving the van for the given number of miles over the given number of mintues.
	 * 
	 * @param miles The amount of miles driven.
	 * @param minutesDrivings The amount of minutesDrivings driven.
	 */
	
	public void drive(int miles, int minutesDrivings) {
		totalMiles+=miles*Math.max(1, riders);
		minutesDriving = minutesDrivings;
		totalTime+=minutesDriving;
		timeDriven+=minutesDriving*Math.min(1, riders);
	}
	
	/**
	 * Reduces the number of riders by 1 but can't go below 0, collects the given tip in dollars.
	 * 
	 * @param tip The tip amount.
	 * @return Nothing; mutates itself.
	 */
	public void dropOff(double tip) {
		totalTip+=tip*Math.min(riders, 1);
		riders--;
		riders = Math.max(riders, 0);
		
	}
	/**
	 * Returns the average hourly pay for the driver by diving the total pay by the time. Total pay includes wages, tips, and bonus points.
	 * @param dollarsPerPoint Used to compute the money in dollars the driver will get from bonus points
	 * @return Returns the average hourly pay for the driver
	 */
	public double getAverageHourlyPay(double dollarsPerPoint) {
		return (getTotalPay(dollarsPerPoint) / (Double.valueOf(totalTime)/60.0));
	}
	/**
	 * Gets the number of riders currently in the van and returns the number
	 * @return the number of people currently in the van
	 */
	public int getRiderCount() {
		return Math.min(maxRiders, riders);
		
	}
	/**
	 * Returns the total miles driven as a sum since the van was constructed
	 * @return the total amount of miles driven from start to finish since van was constructed
	 */
	public int getTotalMiles() {
		return this.totalMiles;
	}
	/**
	 * Computes the total pay earned by the driver since the van was constructed including wages, tips, and bonus points, using the given multiplier to convert bonus points to dollars
	 * @param dollarsPerPoint Used to compute the money in dollars the driver will get from bonus points
	 * @return the total amount of money the driver made after van was constructed
	 */
	public double getTotalPay(double dollarsPerPoint) {
		return ((Double.valueOf(hourlyRate)*Double.valueOf((totalTime/60.0))) + totalTip + (getTotalPoints()*dollarsPerPoint));
	}
	/**
	 * Returns the total bonus points earned by the driver since this van was constructed
	 * @return total amount of points calculated by totalMiles driven and the amount of time spent driving
	 */
	public int getTotalPoints() {
		return (totalMiles*mileageBonus)+(timeDriven*timeBonus*Math.min(riders, 1));
	}
	/**
	 * Returns the total time spent by the driver since this van was constructed in minutesDrivings
	 * @return the total time the driver spent 
	 */
	public int getTotalTime() {
		return this.totalTime;
	}
	/**
	 * Returns all of the tips as a total earned by the driver in dollars
	 * @return the total amount of tips the driver earned
	 */
	public double getTotalTips() {
		return totalTip;
	}
	/**
	 * Increases the number of riders by 1, does nothing if there's already max number of riders in the van
	 */
	public void pickUp() {
		riders++;
		riders = Math.min(maxRiders, riders);
	}
	/**
	 * Simulates the passage of time without the van moving
	 * @param minutesDrivings How long the driver waited for
	 */
	public void waitAround(int minutesDrivings) {
		totalTime += minutesDrivings;
		timeDriven += minutesDrivings*Math.min(riders, 1);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
