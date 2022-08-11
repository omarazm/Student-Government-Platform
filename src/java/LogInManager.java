/*
Omar Abouelazm
Period: 3

This class manages log in information for the website
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class LogInManager
{
    private String email;
    private String password;
    
    public LogInManager()
    {
        email = "N/a";
        password = "N/a";
    }
    public LogInManager(String pUserName, String pEmail, String pPassword)
    {
        email = pUserName;
        password = pPassword;
    }
    //sets info
    public void setEmail(String pEmail)
    {
        this.email = pEmail;
    }
    //sets info
    public void setPassword(String pPassword)
    {
        this.password = pPassword;
    }
    //gets info
    public String getEmail()
    {
        return this.email;
    }
    //gets info
    public String getPassword()
    {
        return this.password;
    }
    
    //Checks if the user entered a valid login
    public boolean checkLogIn()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Password"};
        Object[][] data;
        
        //Checks if there is data within the database
        try
        {
            data = objDb.to2dArray(objDb.getData("ProfileInformation", profileColumnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return false;
        }
        
        //Closes connection
        objDb.closeDbConn();
        
        //Checks if user log in is valid
        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()) && 
                    password.equals(data[i][1].toString()))
            {
                 return true;
            }
        }
        return false;
    }
    //Adds user profile to the database
    public boolean addProfile()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Password"};
        Object[][] data;
        
        String profileQuery = "INSERT INTO ProfileInformation VALUES (?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(profileQuery);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.executeUpdate();
        } 
        catch (SQLException se)
        {
            se.printStackTrace(System.err);
            
            return false;
        }
        
        return true;
    }
    //Updates profile information within the database
    public void updateProfile()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Password"};
        Object[][] data;
        
        String profileQuery = "UPDATE ProfileInformation SET Password = ? WHERE Email = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(profileQuery);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.executeUpdate();
        } 
        catch (SQLException se)
        {
            se.printStackTrace(System.err);
        }
    }
    //sends email 
    public boolean sendEmail(String headerText, String mainText)
    {
        String reciever = email; 
        
        System.out.println(email);
        
        String from = "villagehouses8877@gmail.com";
        
        System.out.println(email + ")//////////////////////////////////");
        
        String smtpServ = "smtp.gmail.com";
        
        String message = "Hello, <br><br>";
        message += mainText + "<br><br>";
        message += "From,<br>";
        message += "The Village Houses Team";
        
        try
        {
            // Get system properties
            Properties properties = System.getProperties();

            properties.put("mail.smtp.host",smtpServ);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.transport.protocol", "smtp" );
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable","true" );
            
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(properties, auth);
            
            // -- Create a new message --
            Message msg = new MimeMessage(session);
            
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever, false));
            msg.setSubject("Village Houses: " + headerText);
            msg.setContent(message, "text/html");
            
            // -- Set some other header information --
            msg.setHeader("MyMail", "Mr. XYZ");
            msg.setSentDate(new Date());
            
            // -- Send the message --
            Transport.send(msg);
        }
        catch (MessagingException mex) 
        {
           mex.printStackTrace();
           System.out.println("HMMM");
           return false;
        }
        
        System.out.println("YAYAY");
        return true;
    }
    //Sends email from my email address
    private class SMTPAuthenticator extends javax.mail.Authenticator 
    {
        @Override
        public PasswordAuthentication getPasswordAuthentication() 
        {
            String userName =  "villagehouses8877@gmail.com";           
            String password = "VillageHouses1223";                                      
            return new PasswordAuthentication(userName, password);
        }
    }
    //Checks if the email is valid
    public boolean checkValidEmail()
    {
        boolean isValid = false;
        
        try
        {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid=true;
        }
        catch(AddressException e)
        {
            
        }
        
        return isValid;
    }
    //Checks if the user entered a duplicate user name when they sign up
    public boolean checkSignUp()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Password"};
        Object[][] data;
        
        //Checks if there is data within the database
        try
        {
            data = objDb.to2dArray(objDb.getData("ProfileInformation", profileColumnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return false;
        }
        
        //Closes connection
        objDb.closeDbConn();
        
        //Checks if user log in is valid
        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                 return true;
            }
        }
        return false;
    }
    //Obtains a user's password
    public String getRegisteredPassword()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Password"};
        Object[][] data;
        
        //Checks if there is data within the database
        try
        {
            data = objDb.to2dArray(objDb.getData("ProfileInformation", profileColumnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return "N/A";
        }
        
        //Closes connection
        objDb.closeDbConn();
        
        //Checks if user log in is valid
        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                 return data[i][1].toString();
            }
        }
        return "N/A";
    }
    
//    Test method
//    public void print()
//    {
//        System.out.println("dasjbdnaisbdais");
//    }
    
    //Generates a verification code
    public String generateVerificationCode()
    {
        final char[] CHARS = {'0','1', '2', '3', '4', '5', '6', '7', '8',  
        '9','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',  
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',  
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };  
        
        Random random = new Random();  
        
        StringBuffer buffer = new StringBuffer();  
        
        // generate 5 digit and character  
        for (int i = 0; i < 5; i++) 
        {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);  
        }  
        
        return buffer.toString(); 
    }
    //inserts verification code into database
    public void insertVerificationCode(String verificationCode)
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        
        String profileQuery = "INSERT INTO AccountVerificationCodes VALUES (?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(profileQuery);
            ps.setString(1, email);
            ps.setString(2, verificationCode);
            ps.setString(3, password);
            ps.executeUpdate();
        } 
        catch (SQLException se)
        {
            se.printStackTrace(System.err);
        }
    }
    
    //inserts verification code in the database
    public void updateVerificationCode(String verificationCode)
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        
        String profileQuery = "UPDATE AccountVerificationCodes SET VerificationCode = ?, Password = ? WHERE Email = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(profileQuery);
            ps.setString(1, verificationCode);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();
        } 
        catch (SQLException se)
        {
            se.printStackTrace(System.err);
        }
    }
    
    //deletes verification data
    public void deleteVerification()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        
        String profileQuery = "DELETE FROM AccountVerificationCodes WHERE Email = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(profileQuery);
            ps.setString(1, email);
            ps.executeUpdate();
        } 
        catch (SQLException se)
        {
            se.printStackTrace(System.err);
        }
    }
    //Sends verification code
    public void sendVerificationCode()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] verificationColumnName = {"Email", "VerificationCode", "Password"};
        Object[][] data;
        
        String verificationCode = generateVerificationCode();
        
        boolean isUniqueCode = false;
        boolean isSameEmail = false;
        
        //Checks if there is data within the database
        try
        {
            data = objDb.to2dArray(objDb.getData("AccountVerificationCodes", verificationColumnName));
            
            //Checks if the verification code, that was sent, is unique (Security purposes)
            while (isUniqueCode == false)
            {
                for (int i=0; i<data.length; i++)
                {
                    if (verificationCode.equals(data[i][1].toString()))
                    {
                        isUniqueCode = false;
  
                        verificationCode = generateVerificationCode();
                    }
                    else
                    {
                        isUniqueCode = true;
                    }
                }              
            }
            
            for (int i=0; i<data.length; i++)
            {
                if (email.equalsIgnoreCase(data[i][0].toString()))
                {
                    isSameEmail = true;
                }
            }      
        }
        catch(IndexOutOfBoundsException ibe)
        {
            isUniqueCode = true;
        }
        
        if (isSameEmail == false)
        {
            insertVerificationCode(verificationCode);
        }
        else
        {
            updateVerificationCode(verificationCode);
        }
        
        //Sends email
        sendEmail("Verify Account", "Here is your verification code: " + verificationCode);
    }
    //Checks if verification is valid
    public boolean checkVerificationCode(String verificationCode)
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] verificationColumnName = {"Email", "VerificationCode", "Password"};
        Object[][] data;
        
        data = objDb.to2dArray(objDb.getData("AccountVerificationCodes", verificationColumnName));

        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()) && verificationCode.equals(data[i][1].toString()))
            {
                password = data[i][2].toString();
                return true;
            }
        }     
        
        return false;
    }
    //Checks if an account is verified
    public boolean checkAccountVerification()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] verificationColumnName = {"Email", "VerificationCode", "Password"};
        Object[][] data;
        
        try
        {
            data = objDb.to2dArray(objDb.getData("AccountVerificationCodes", verificationColumnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return false;
        }

        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                return true;
            }
        }     
        
        return false;
    }
    /*Checks if a profile is registered 
    in one of the houses for the house database */
    public boolean checkHouseRegistration()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] houseColumnName = {"Email", "MemberName", "HouseType"};
        Object[][] data;
        
        try
        {
            data = objDb.to2dArray(objDb.getData("HouseMembers", houseColumnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return false;
        }

        //Checks if specified email is in the data
        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                return true;
            }
        }     
        
        return false;
    }
    public static void main(String[] args)
    {
        LogInManager logInObj = new LogInManager();
        logInObj.setEmail("omar_abouelazm@s.thevillageschool.com");
        logInObj.sendEmail("WOAH", "COOL");
        System.out.println(logInObj.generateVerificationCode());
    }
}
