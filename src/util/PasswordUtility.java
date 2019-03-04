package util;

import java.security.MessageDigest;

public class PasswordUtility 
{
	
	public static String generatePassword(String passwordToHash)
	{
	    String generatedPassword = "";
	    try {
	        // Create MessageDigest instance for MD5
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        //Add password bytes to digest
	        md.update(passwordToHash.getBytes());
	        //Get the hash's bytes
	        byte[] bytes = md.digest();
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        //Get complete hashed password in hex format
	        generatedPassword = sb.toString();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		return generatedPassword;
	}
}

