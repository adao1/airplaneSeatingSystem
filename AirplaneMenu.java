//Alex Dao

import java.util.Scanner;

//This class allows processing of user input for interaction with Airplane
public class AirplaneMenu
{
	private Airplane myAirplane;
	public static final int FIRSTWINDOW = 0;
	public static final int FIRSTAISLE = 1;
	public static final int ECONWINDOW = 0;
	public static final int ECONCENTER = 1;
	public static final int ECONAISLE = 2;

	public AirplaneMenu()
	{
		myAirplane = new Airplane();
	}


	//calls appropriate method based on input
	public void run()
	{
		Scanner input = new Scanner(System.in);

		String response = "";

		while(!response.equalsIgnoreCase("Q"))
		{
			System.out.println("A)dd  S)how  Q)uit");
			response = input.nextLine();

			if (response.equalsIgnoreCase("A"))
			{
				this.add();
				System.out.println();
			}

			if(response.equalsIgnoreCase("S"))
			{
				this.show();
				System.out.println();
			}
		}
	}

	//adds seat by processing what preferences are desired
	public void add()
	{
		Scanner input = new Scanner(System.in);

		String classResponse, preferenceResponse;
		Integer passengerResponse;
		System.out.println("F)irst  E)conomy");
		classResponse = input.nextLine();

		//if first class is selected, first class-specifc options are given
		if(classResponse.equalsIgnoreCase("F"))
		{
			System.out.println("Passengers? (1-2)");
			passengerResponse = Integer.parseInt(input.nextLine());

			if(passengerResponse == 1)
			{
				System.out.println("A)isle  W)indow");
				preferenceResponse = input.nextLine();
				if(preferenceResponse.equalsIgnoreCase("A"))
				{
					String filled = myAirplane.fillPartial(passengerResponse,FIRSTAISLE,classResponse);
					if(filled.length() > 0)
					{
						System.out.println(filled);
					}
				}

				if(preferenceResponse.equalsIgnoreCase("W"))
				{
					String filled = myAirplane.fillPartial(passengerResponse,FIRSTWINDOW, classResponse);
					if(filled.length() > 0)
					{
						System.out.println(filled);
					}
				}
			}

			if(passengerResponse == 2)
			{
				String filled = myAirplane.fillRow(classResponse);
				if(filled.length() > 0)
				{
					System.out.println(filled);
				}

			}
		}

		//economy class options are given if economy is selected
		if(classResponse.equalsIgnoreCase("E"))
		{
			System.out.println("Passengers? (1-3)");
			passengerResponse = Integer.parseInt(input.nextLine());

			if(passengerResponse == 1 || passengerResponse == 2)
			{
				System.out.println("A)isle  C)enter  W)indow");
				preferenceResponse = input.nextLine();

				if(preferenceResponse.equalsIgnoreCase("A"))
				{
					String filled = myAirplane.fillPartial(passengerResponse,ECONAISLE,classResponse);
					if(filled.length() > 0)
					{
						System.out.println(filled);
					}

				}

				if(preferenceResponse.equalsIgnoreCase("C"))
				{
					String filled = myAirplane.fillPartial(passengerResponse,ECONCENTER,classResponse);
					if(filled.length() > 0)
					{
						System.out.println(filled);
					}

				}

				if(preferenceResponse.equalsIgnoreCase("W"))
				{
					String filled = myAirplane.fillPartial(passengerResponse,ECONWINDOW,classResponse);
					if(filled.length() > 0)
					{
						System.out.println(filled);
					}

				}
			}

			if(passengerResponse == 3)
			{
				String filled = myAirplane.fillRow(classResponse);
				if(filled.length() > 0)
				{
					System.out.println(filled);
				}
			}
		}
	}

	//prints the airplane seat configuration for the user to see
	public void show()
	{
		System.out.println(myAirplane.firstClassToString());
		System.out.println(myAirplane.economyToString());
	}
}