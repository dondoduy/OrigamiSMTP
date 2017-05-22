package com.pessetto.CommandHandlers;

import com.pessetto.CommandHandlers.Interfaces.Validatable;
import com.pessetto.Common.Variables;

public class RCPTHandler implements Validatable {
	
	private String Response;
	private String ToEmail;
	private boolean valid;
	public RCPTHandler(String fullCmd,MAILHandler mail)
	{
		String[] parts = fullCmd.split(" ",2);
		valid = false;
		if(mail == null)
		{
			Response = "503 Sender required before recipient"+Variables.CRLF;
			valid = false;
		}
		else if(parts.length > 1)
		{
			parts = parts[1].split(":",2);
			if(parts.length > 1)
			{
				ToEmail = parts[1];
				Response = "250 OK"+Variables.CRLF;
				valid = true;
			}
			else
			{
				Response = "501 Syntax Error could not seperate from and email"+Variables.CRLF;
				valid = false;
			}
		}
		else
		{
			Response = "501 Syntax Error could net seperate cmd and cmdId" + Variables.CRLF;
			valid = false;
		}
	}

	public String GetRecipient()
	{
		return ToEmail;
	}
	
	public String GetResponse()
	{
		return Response;
	}

	@Override
	public Validatable ValidateOrNullify() 
	{
		return valid ? this : null;
	}
}
