//Alex Dao

//This class represents an airplane with a given seat configuration
public class Airplane
{
	private String[][] firstClassLeft, firstClassRight, economyLeft, economyRight;
	public static final int ECON_ROWS = 15;
	public static final int ECON_COLUMNS = 3;
	public static final int FIRST_ROWS = 5;
	public static final int FIRST_COLUMNS =2;


	//constructs the 2D arrays to represent the seats in the airplane seperated by an aisle
	public Airplane()
	{
		firstClassLeft = new String[FIRST_ROWS][FIRST_COLUMNS];
		firstClassRight = new String[FIRST_ROWS][FIRST_COLUMNS];
		economyLeft = new String[ECON_ROWS][ECON_COLUMNS];
		economyRight = new String[ECON_ROWS][ECON_COLUMNS];

		setSeats(firstClassRight);
		setSeats(firstClassLeft);
		setSeats(economyLeft);
		setSeats(economyRight);
	}

	//sets all the places in all the arrays to ".", which represents an empty seat
	public void setSeats(String[][] mySeatingArrangement)
	{
		for(int i = 0; i < mySeatingArrangement.length; i++)
		{
			for(int j = 0; j < mySeatingArrangement[0].length; j++)
			{
				mySeatingArrangement[i][j] = ".";
			}
		}
	}

	//this method allows for specific filling of seats based on the parameters
	public String fillPartial(int numOfSeats, int seatPreference, String seatClass)
	{
		//if first class is selected, the method takes in to account preference and then finds the matching open seat
		//otherwise it returns a notification
		if (seatClass.equalsIgnoreCase("F"))
		{
			boolean matchFound = false;

			//first class partial can be only 1 seat with a certain preference
			for(int i = 0; i < firstClassLeft.length; i++)
			{
				if(firstClassLeft[i][seatPreference].equals("."))
				{
					firstClassLeft[i][seatPreference] = "*";
					i = firstClassLeft.length + 1;
					matchFound = true;
				}

				else if (firstClassRight[i][seatPreference].equals("."))
				{
					firstClassRight[i][seatPreference] = "*";
					i = firstClassLeft.length + 1;
					matchFound = true;
				}
			}

			if(!matchFound)
			{
				return "No match exists for the seat requested.";
			}
		}

		//for economy seats, it takes into account preference and also whether an open seat is to the left or right if 2 seats are selected
		if (seatClass.equalsIgnoreCase("E"))
		{
			boolean matchFound = false;
			for(int i = 0; i < ECON_ROWS; i++)
			{
				if(economyLeft[i][seatPreference].equals("."))
				{
					//if two seats out of 3 are selected, then the preference must be determined and there must be a match found
					if(numOfSeats == 2)
					{
						//looks to see if seat to the right is also open for a window preferenced seat
						if(seatPreference == 0 && economyLeft[i][seatPreference + 1].equals("."))
						{
								economyLeft[i][seatPreference] = "*";
								economyLeft[i][seatPreference + 1] = "*";
								i = ECON_ROWS + 1;
								matchFound = true;
						}

						if(seatPreference == 1)
						{
							//if seat in the middle is preferred, then we look for an open seat next to it
							if (economyLeft[i][seatPreference + 1].equals("."))
							{
								economyLeft[i][seatPreference] = "*";
								economyLeft[i][seatPreference + 1] = "*";
								i = ECON_ROWS + 1;
								matchFound = true;
							}

							else if (economyLeft[i][seatPreference - 1].equals("."))
							{
								economyLeft[i][seatPreference] = "*";
								economyLeft[i][seatPreference - 1] = "*";
								i = ECON_ROWS + 1;
								matchFound = true;
							}
						}

						if(seatPreference == 2 && economyLeft[i][seatPreference - 1].equals("."))
						{
								economyLeft[i][seatPreference] = "*";
								economyLeft[i][seatPreference - 1] = "*";
								i = ECON_ROWS + 1;
								matchFound = true;
						}
					}

					//finds an open seat with specified preference
					if(numOfSeats == 1)
					{
						economyLeft[i][seatPreference] = "*";
						i = ECON_ROWS + 1;
						matchFound = true;
					}
				}

				//does the same thing concurrently, but on the right of the plane
				else if(economyRight[i][seatPreference].equals("."))
				{
					if(numOfSeats == 2)
					{
						if(seatPreference == 0 && economyRight[i][seatPreference + 1].equals("."))
						{
								economyRight[i][seatPreference] = "*";
								economyRight[i][seatPreference + 1] = "*";
								i = ECON_ROWS + 1;
								matchFound = true;
						}

						if(seatPreference == 1)
						{
							if (economyRight[i][seatPreference + 1].equals("."))
							{
								economyRight[i][seatPreference] = "*";
								economyRight[i][seatPreference + 1] = "*";
								i = ECON_ROWS + 1;
								matchFound = true;
							}

							else if (economyRight[i][seatPreference - 1].equals("."))
							{
								economyRight[i][seatPreference] = "*";
								economyRight[i][seatPreference - 1] = "*";
								i = ECON_ROWS + 1;
								matchFound = true;
							}
						}

						if(seatPreference == 2 && economyRight[i][seatPreference - 1].equals("."))
						{
								economyRight[i][seatPreference] = "*";
								economyRight[i][seatPreference - 1] = "*";
								i = ECON_ROWS + 1;
								matchFound = true;
						}
					}

					if(numOfSeats == 1)
					{
						economyRight[i][seatPreference] = "*";
						i = ECON_ROWS + 1;
						matchFound = true;
					}
				}
			}

			//each time a match is found, the boolean would be set to true
			//if no match was found, it informs the user of that
			if(!matchFound)
			{
				return "No match exists for the seat requested.";
			}
		}

		return ""; //returns an empty string if a match is found
	}


	//since the rows have set sizes, this method finds the first open row given the class and fills it using "*"
	public String fillRow(String seatClass)
	{
		if (seatClass.equalsIgnoreCase("F"))
		{
			boolean matchFound = false;
			for(int i = 0; i < firstClassLeft.length; i++)
			{
				if(firstClassLeft[i][0].equals(".") && firstClassLeft[i][1].equals("."))
				{
					firstClassLeft[i][0] = "*";
					firstClassLeft[i][1] = "*";
					i = firstClassLeft.length + 1;
					matchFound = true;
				}

				else if(firstClassRight[i][0].equals(".") && firstClassRight[i][1].equals("."))
				{
					firstClassRight[i][0] = "*";
					firstClassRight[i][1] = "*";
					i = firstClassLeft.length + 1;
					matchFound = true;
				}
			}

			if(!matchFound)
			{
				return "No match exists for the seat requested.";
			}

		}

		if (seatClass.equalsIgnoreCase("E"))
		{
			boolean matchFound = false;
			for(int i = 0; i < economyLeft.length; i++)
			{
				if(economyLeft[i][0].equals(".") && economyLeft[i][1].equals(".") && economyLeft[i][2].equals("."))
				{
					economyLeft[i][0] = "*";
					economyLeft[i][1] = "*";
					economyLeft[i][2] = "*";
					i = economyLeft.length + 1;
					matchFound = true;
				}

				else if(economyRight[i][0].equals(".") && economyRight[i][1].equals(".") && economyRight[i][2].equals("."))
				{
					economyRight[i][0] = "*";
					economyRight[i][1] = "*";
					economyRight[i][2] = "*";
					i = economyLeft.length + 1;
					matchFound = true;
				}
			}

			if(!matchFound)
			{
				return "No match exists for the seat requested.";
			}
		}

		return "";//returns empty string if no matches are found
	}

	//processes the first class seating arrangement into one string by combining the two 2D arrays to represent the two sides of the plane
	public String firstClassToString()
	{
		String resultString = "";

		for(int i = 0; i < FIRST_ROWS; i++)
		{
			resultString += " " + (i+1) + ":";

			for(int j = 0; j < FIRST_COLUMNS; j++)
			{
				resultString += firstClassLeft[i][j];

				if((j+1) == 2)
					resultString += "  ";
				else
					resultString += "   ";
			}

			for(int j = 0; j < FIRST_COLUMNS; j++)
			{
				resultString += firstClassRight[i][j] + "   ";
			}

			if((i+1) < 5)
				resultString += "\n";
		}

		return resultString;
	}

	//processes the economy 2D arrays in to a single string for the user to see, representing the two sides of the economy class
	public String economyToString()
	{
		String resultString = "";

		for(int i = 0; i < ECON_ROWS; i++)
		{
			if((i+6) < 10)
			{
				resultString += " ";
			}
			resultString += (i+6) + ":";

			for(int j = 0; j < ECON_COLUMNS; j++)
			{
				resultString += economyLeft[i][j] + " ";
			}

			resultString += " ";

			for(int j = 0; j < ECON_COLUMNS; j++)
			{
				resultString += economyRight[i][j] + " ";
			}

			resultString += "\n";
		}

		return resultString;
	}
}
