
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/*
Omar Abouelazm
Period: 3

This class manages house information for the website
 */

public class HouseManager
{
    private String email;
    private String memberName;
    private String houseType;
    private String housePosition;
    private int gradeLevel;
    
    public void print(String stuff)
    {
        System.out.println("Hello: " + stuff);
    }
    public HouseManager()
    {
        email = "N/a";
        memberName = "N/a";
        houseType = "N/a";
        housePosition = "N/a";
        gradeLevel = 0;
    }
    public HouseManager(String pUserName, String pMemberName, String pHouseType, String pHousePosition, int pGradeLevel)
    {
        email = pUserName;
        memberName = pMemberName;
        houseType = pHouseType;
        housePosition = pHousePosition;
        gradeLevel = pGradeLevel;
    }
    //sets info
    public void setEmail(String pEmail)
    {
        this.email = pEmail;
    }
    public void setMemberName(String pMemberName)
    {
        this.memberName = pMemberName;
    }
    public void setHouseType(String pHouseType)
    {
        this.houseType = pHouseType;
    }
    public void setHousePosition(String pHousePosition)
    {
        this.housePosition = pHousePosition;
    }
    public void setGradeLevel(int pGradeLevel)
    {
        this.gradeLevel = pGradeLevel;
    }
    
    //gets info
    public String getEmail()
    {
        return this.email;
    }
    public String getMemberName()
    {
        return this.memberName;
    }
    public String getHouseType()
    {
        return this.houseType;
    }
    public String getHousePosition()
    {
        return this.housePosition;
    }
    public int getGradeLevel()
    {
        return this.gradeLevel;
    }
    
    //Finds a user's house type
    public String findHouseType()
    {
        Connection myDbConn = null;
        
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] housesColumnName = {"Email", "MemberName", "HouseType"};
        Object[][] data;
        
        //Checks if there is data within the database
        try
        {
            data = objDb.to2dArray(objDb.getData("HouseMembers", housesColumnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
            
            return "ERROR";
        }
        
        //Closes connection
        objDb.closeDbConn();
        
        //Finds the house type for that specific email
        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                 return data[i][2].toString();
            }
        }
        return "ERROR";
    }
    //Finds the number of announcements made
    public int obtainAnnouncementNumber()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "AnnouncementId", "AnnouncementTitle", "Announcement"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);
        
        int counter = 0;

        Object[][] data;
        
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("Announcements", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
            
            return 0;
        }
        
        for (int i=0; i<data.length; i++)
        {
            counter++;
        }

        return counter;
    }
    //Finds the announcement title for a specified id
    public String obtainAnnouncementTitle(int announcementId)
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "AnnouncementId", "AnnouncementTitle", "Announcement"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);

        Object[][] data = null;
                
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("Announcements", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        for (int i = 0; i<data.length; i++)
        {
           if (Integer.parseInt(data[i][1].toString()) == announcementId)
           {
               return data[i][2].toString();
           }
        }
        
        return "ERROR 404: Not Found";
    }
    //Finds the poll question for a specified id
    public String obtainPollQuestion(int pollId)
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "PollId", "PollQuestions"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);

        Object[][] data = null;
                
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("PollQuestions", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        try
        {
            for (int i = 0; i<data.length; i++)
            {
               if (Integer.parseInt(data[i][1].toString()) == pollId)
               {
                   return data[i][2].toString();
               }
            }
        }
        catch(NullPointerException npe)
        {
                
        }
        
        return "ERROR 404: Not Found";
    }
    //Obtains the announcement data
    public ArrayList<ArrayList<String>> getAnnouncementData()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "AnnouncementId", "AnnouncementTitle", "Announcement"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);

        ArrayList<ArrayList<String>> data = null;
        
        System.out.println("SDnbiabsdiausbndiaubd: " + houseType);
        
        try
        {
            //gets data
            data = objDb.getHouseData("Announcements", columnName, houseType);
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        Collections.reverse(data);
        
        return data;
    }
    //Deletes the announcement with a specified id
    public void deleteAnnouncement(int announcementId)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();
        
        int previousAnnouncementId;
        int newAnnouncementId;
        
        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "AnnouncementId", "AnnouncementTitle", "Announcement"
        };
        Object[][] data = null;
        
        String dbQuery1 = "DELETE FROM Announcements WHERE AnnouncementId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, announcementId);
            ps.setString(2, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error deleting data");
            se.printStackTrace(System.err);
        }
              
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("Announcements", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        try
        {
            for (int i=announcementId; i<data.length; i++)
            {
                newAnnouncementId = i;
                previousAnnouncementId = i + 1;
                shuffleAnnouncements(previousAnnouncementId++, newAnnouncementId);
            }
        }
        catch(NullPointerException npe)
        {
            
        }
        
        //Closes connection
        objDb.closeDbConn();
    }
    //Shuffles the announcement ids when an announcement is deleted
    public void shuffleAnnouncements(int previousAnnouncementId, int newAnnouncementId)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "UPDATE Announcements SET AnnouncementId = ? WHERE AnnouncementId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, newAnnouncementId);
            ps.setInt(2, previousAnnouncementId);
            ps.setString(3, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error updating data");
            se.printStackTrace(System.err);
        }
                   
        //Closes connection
        objDb.closeDbConn();
    }
    //Checks if a user is admin or not
    public String findAuthorization()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "Email", "MemberName", "HousePosition", "GradeLevel"
        };
        JavaSqlAccess objDb2 = new JavaSqlAccess(dbName);
        
        Object[][] data;

        try
        {
            data = objDb2.to2dArray(objDb2.getData("Leadership", columnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return "nonAuthorized";
        }
        
        //Goes through admin data to check if email exists
        for (int i = 0; i < data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                return "authorized";
            }
        }
        
        return "nonAuthorized";
    }
    //inserts announcement data
    public boolean insertAnnouncementData(int id, String title, String description)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "INSERT INTO Announcements VALUES (?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, houseType);
            ps.setInt(2, id);
            ps.setString(3, title);
            ps.setString(4, description);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
            
            return false;
        }
                   
        //Closes connection
        objDb.closeDbConn();
        
        sendAnnouncementNotification(title);
        
        return true;
    }
    //Obtains poll data
    public ArrayList<ArrayList<Object>> getPolls()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] questionColumnNames =
        {
            "HouseType", "PollId", "PollQuestions"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);

        Object[][] questionData = null;
                
        try
        {
            //gets data
            questionData = objDb.to2dArray(objDb.getHouseData("PollQuestions", questionColumnNames, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            //objDb.closeDbConn();
        }
        
        //Creates object of database access
        String[] answerColumnNames =
        {
            "HouseType", "PollId", "PollAnswers"
        };

        Object[][] answerData = null;
                
        try
        {
            //gets data
            answerData = objDb.to2dArray(objDb.getHouseData("PollAnswers", answerColumnNames, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            //objDb.closeDbConn();
        }
        
        ArrayList<ArrayList<Object>> convertedData = new ArrayList<>();
        
        try
        {
            //Goes through the question data
            for (int i = 0; i < questionData.length; i++)
            {
                ArrayList<Object> row = new ArrayList<>();
                
                String answeredPoll;

                row.add(questionData[i][1].toString());

                row.add(questionData[i][2].toString());
                
                answeredPoll = getAnsweredPoll(i);
                
                //Checks if the user answered/voted on the poll with a specific id
                if (answeredPoll.equalsIgnoreCase(""))
                {
                    row.add("authorized");
                    row.add("nonAuthorized");
                }
                else 
                {
                    row.add("nonAuthorized");
                    row.add("authorized");
                }
                
                row.add(answeredPoll);

                //Goes through answer data and obtains the answers and vote number to a specific poll
                for (int j = 0; j < answerData.length; j++)
                {
                    int voteNumber;
                    
                    if (Integer.parseInt(answerData[j][1].toString()) == Integer.parseInt(questionData[i][1].toString()))
                    {
                        voteNumber = getVoteNumber(Integer.parseInt(questionData[i][1].toString()), answerData[j][2].toString());
                        
                        PollAnswer pollAnswer = new PollAnswer(answerData[j][2].toString(), voteNumber);
                        
                        row.add(pollAnswer);
                    }
                }

                convertedData.add(row);
            }
        }
        catch(NullPointerException npe)
        {
            
        }
        
        Collections.reverse(convertedData);
        
        return convertedData;
    }
    //Inserts new poll
    public boolean insertNewPoll(int id, String pollQuestion, ArrayList<String> pollAnswers)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "INSERT INTO PollQuestions VALUES (?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, houseType);
            ps.setInt(2, id);
            ps.setString(3, pollQuestion);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
            
            return false;
        }
        
        for (int i=0; i<pollAnswers.size(); i++)
        {
            String dbQuery2 = "INSERT INTO PollAnswers VALUES (?,?,?)";

            //Uses query to add data to databas
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);
                ps.setString(1, houseType);
                ps.setInt(2, id);
                ps.setString(3, pollAnswers.get(i));
                ps.executeUpdate();
            }
            catch (SQLException se)
            {
                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
                
                return false;
            }
        }
             
        sendPollNotification(pollQuestion);
        
        //Closes connection
        objDb.closeDbConn();
        
        return true;
    }
    //Sends poll notification
    public void sendPollNotification(String pollTitle)
    {
        Object[][] data = getSettingsData();
        
        String allowAll = "allowAll";
        String allowPollOnly = "onlyPolls";
        
        LogInManager logInObj = new LogInManager();
        
        //Goes through notification data
        for (int i = 0; i < data.length; i++)
        {
            if(allowAll.equals(data[i][1].toString()) || allowPollOnly.equals(data[i][1].toString()))
            {
                setEmail(data[i][0].toString());
                
                //Checks if their house type is that of the house that triggered a notification
                if (houseType.equalsIgnoreCase(findHouseType()))
                {                
                    logInObj.setEmail(data[i][0].toString());
                
                    logInObj.sendEmail("New Poll", "A new poll has been created titled: " + "'" + pollTitle + "'");
                }
            }
        }
    } 
    //Obtains a user's notification type
    public String findNotificationType()
    {
        Object[][] data = getSettingsData();
        
        //Goes through the notification data 
        for (int i = 0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                if ((data[i][1].toString()).equalsIgnoreCase("allowAll"))
                {
                    return "All";
                }
                else if ((data[i][1].toString()).equalsIgnoreCase("onlyAnnouncements"))
                {
                    return "Only Announcements";
                }
                else if ((data[i][1].toString()).equalsIgnoreCase("onlyPolls"))
                {
                    return "Only Polls";
                }
            }
        }
        
        return "None";
    }
    //Sends announcement notification
    public void sendAnnouncementNotification(String announcementTitle)
    {
        Object[][] data = getSettingsData();
        
        String allowAll = "allowAll";
        String allowAnnouncementOnly = "onlyAnnouncements";
        
        LogInManager logInObj = new LogInManager();
        
        //Goes through notification data
        for (int i = 0; i < data.length; i++)
        {
            if(allowAll.equals(data[i][1].toString()) || allowAnnouncementOnly.equals(data[i][1].toString()))
            {
                setEmail(data[i][0].toString());
                
                //Checks if their house type is that of the house that triggered a notification
                if (houseType.equalsIgnoreCase(findHouseType()))
                {                                  
                    logInObj.setEmail(data[i][0].toString());
                
                    logInObj.sendEmail("New Announcement", "A new announcement has been created titled: " + "'" + announcementTitle + "'");
                }
            }
        }
    }
    //Inserts user's poll answer/vote
    public void insertPollAnswer(int id, String answer)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "INSERT INTO AnsweredPolls VALUES (?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, houseType);
            ps.setString(2, email);
            ps.setInt(3, id);
            ps.setString(4, answer);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }
                   
        //Closes connection
        objDb.closeDbConn();     
    }
    //Obtains the number of polls
    public int obtainPollNumber()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "PollId", "PollQuestions"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);
        
        int counter = 0;

        Object[][] data = null;
        
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("PollQuestions", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        catch(NullPointerException npe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        try
        {
            for (int i=0; i<data.length; i++)
            {
                counter++;
            }
        }
        catch (NullPointerException npe)
        {
            
        }
        
        return counter;
    }
    //Delete the specified poll
    public void deletePoll(int pollId)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();
        
        int previousPollId;
        int newPollId;
        
        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "PollId", "PollQuestions"
        };
        Object[][] data = null;
        
        String dbQuery1 = "DELETE FROM PollQuestions WHERE PollId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, pollId);
            ps.setString(2, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error deleting data");
            se.printStackTrace(System.err);
        }
        
        String dbQuery2 = "DELETE FROM PollAnswers WHERE PollId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);
            ps.setInt(1, pollId);
            ps.setString(2, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error deleting data");
            se.printStackTrace(System.err);
        }
        
        String dbQuery3 = "DELETE FROM AnsweredPolls WHERE PollId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery3);
            ps.setInt(1, pollId);
            ps.setString(2, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error deleting data");
            se.printStackTrace(System.err);
        }
              
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("PollQuestions", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        try
        {
            for (int i=pollId; i<data.length; i++)
            {
                newPollId = i;
                previousPollId = i + 1;
                shufflePolls(previousPollId++, newPollId);
            }
        }
        catch(NullPointerException npe)
        {
            
        }
        
        //Closes connection
        objDb.closeDbConn();
    }
    //Shuffles the poll ids
    public void shufflePolls(int previousPollId, int newPollId)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "UPDATE PollQuestions SET PollId = ? WHERE PollId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, newPollId);
            ps.setInt(2, previousPollId);
            ps.setString(3, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error updating data");
            se.printStackTrace(System.err);
        }
               
        String dbQuery2 = "UPDATE PollAnswers SET PollId = ? WHERE PollId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);
            ps.setInt(1, newPollId);
            ps.setInt(2, previousPollId);
            ps.setString(3, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error updating data");
            se.printStackTrace(System.err);
        }
        
        String dbQuery3 = "UPDATE AnsweredPolls SET PollId = ? WHERE PollId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery3);
            ps.setInt(1, newPollId);
            ps.setInt(2, previousPollId);
            ps.setString(3, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error updating data");
            se.printStackTrace(System.err);
        }
        
        //Closes connection
        objDb.closeDbConn();
    }
    //Obtains the user answer/vote for a specified poll
    public String getAnsweredPoll(int id)
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "Email", "PollId", "MemberPollAnswer"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);

        Object[][] data = null;
        
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("AnsweredPolls", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        try
        {
            for (int i=0; i<data.length; i++)
            {
                if (email.equalsIgnoreCase(data[i][1].toString()) && id == Integer.parseInt(data[i][2].toString()))
                {
                    return data[i][3].toString();
                }
            }
        }
        catch (NullPointerException npe)
        {
            
        }
        
        return "";
    }
    //Obtains the number of votes
    public int getVoteNumber(int pollId, String answer)
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "Email", "PollId", "MemberPollAnswer"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);
        
        int counter = 0;

        Object[][] data = null;
        
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("AnsweredPolls", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        try
        {
            for (int i=0; i<data.length; i++)
            {
                if (pollId == Integer.parseInt(data[i][2].toString()) 
                        && answer.equalsIgnoreCase(data[i][3].toString()))
                {
                    counter++;
                }
            }
        }
        catch (NullPointerException npe)
        {
            
        }
        
        return counter;
    }
    //Removes a user's answer/vote for a specific poll
    public void removeAnsweredPoll(int pollId)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();
        
        String dbQuery1 = "DELETE FROM AnsweredPolls WHERE Email = ? AND PollId = ? AND HouseType = ?";

        //Uses query to delete data from database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, email);
            ps.setInt(2, pollId);
            ps.setString(3, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error deleting data");
            se.printStackTrace(System.err);
        }
        
        //Closes connection
        objDb.closeDbConn();
    }
    //Saves notification type
    public void saveNotifications(String notificationType)
    {
        Object[][] data = getSettingsData();
        
        boolean updated = false;
        
        try
        {
            for (int i=0; i<data.length; i++)
            {
                if (email.equals(data[i][0].toString()))
                {
                    updateNotifications(notificationType);
                    
                    updated = true;
                }
            }
            
            //Checks if the user has already specified a notification type
            if (updated == false)
            {
                addNotifications(notificationType);
            }
        }
        catch (NullPointerException npe)
        {
            addNotifications(notificationType);
        }
    }
    //Updates notification data
    public void updateNotifications(String notificationType)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "UPDATE ProfileSettings SET NotificationType = ? WHERE Email = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, notificationType);
            ps.setString(2, email);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error updating data");
            se.printStackTrace(System.err);
        }
        
        //Closes connection
        objDb.closeDbConn();
    }
    //inserts notification data
    public void addNotifications(String notificationType)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "INSERT INTO ProfileSettings VALUES (?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, email);
            ps.setString(2, notificationType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error updating data");
            se.printStackTrace(System.err);
        }
        
        //Closes connection
        objDb.closeDbConn();
    }
    //Obtains settings data
    public Object[][] getSettingsData()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "Email", "NotificationType"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);

        Object[][] data = null;
        
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getData("ProfileSettings", columnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        return data;
    }
    //Inserts/sends feedback
    public boolean insertFeedback(int feedbackId, String feedback)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "INSERT INTO HouseFeedback VALUES (?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, houseType);
            ps.setInt(2, feedbackId);
            ps.setString(3, email);
            ps.setString(4, feedback);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error updating data");
            se.printStackTrace(System.err);
            
            return false;
        }
        
        //Closes connection
        objDb.closeDbConn();
        
        return true;
    }
    //Obtains the number of feedback
    public int obtainFeedbackNumber()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "FeedbackId", "Email", "Feedback"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);
        
        int counter = 0;

        Object[][] data;
        
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getData("HouseFeedback", columnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
            
            return 0;
        }
        
        for (int i=0; i<data.length; i++)
        {
            if ((data[i][0].toString()).equalsIgnoreCase(houseType))
            {
                counter++;
            }
        }

        return counter;
    }
    //Obtains the house leadership data
    public ArrayList<ArrayList<String>> getLeadershipData()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] leadershipColumnNames =
        {
            "Email", "MemberName", "HousePosition", "GradeLevel"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);

        Object[][] leadershipData = null;
                
        try
        {
            //gets data
            leadershipData = objDb.to2dArray(objDb.getData("Leadership", leadershipColumnNames));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            //objDb.closeDbConn();
        }
        
        //Creates object of database access
        String[] memberColumnNames =
        {
            "Email", "MemberName", "HouseType"
        };

        Object[][] memberData = null;
                
        try
        {
            //gets data
            memberData = objDb.to2dArray(objDb.getHouseData("HouseMembers", memberColumnNames, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            //objDb.closeDbConn();
        }
        
        ArrayList<ArrayList<String>> convertedData = new ArrayList<>();
        
        try
        {
            //Obtains leaders for a specific house
            for (int i = 0; i < leadershipData.length; i++)
            {
                ArrayList<String> row = new ArrayList<>();
                
                for (int j = 0; j < memberData.length; j++)
                {                 
                    if ((memberData[j][0].toString()).equalsIgnoreCase(leadershipData[i][0].toString()))
                    {
                        row.add(leadershipData[i][1].toString());
                        row.add(leadershipData[i][2].toString());
                        row.add(leadershipData[i][3].toString());
                        
                        convertedData.add(row);
                        
                        break;
                    }
                }
            }
        }
        catch(NullPointerException npe)
        {
            
        }
        catch(IndexOutOfBoundsException ibe)
        {
            
        }
        
        Collections.reverse(convertedData);
        
        return convertedData;
    }
    //Obtains feedback data
    public ArrayList<ArrayList<String>> getFeedbackData()
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] feedbackColumnNames =
        {
            "HouseType", "FeedbackId", "Email", "Feedback"
        };
        JavaSqlAccess objDb = new JavaSqlAccess(dbName);

        ArrayList<ArrayList<String>> feedbackData = null;
                
        try
        {
            //gets data
            feedbackData = objDb.getHouseData("HouseFeedback", feedbackColumnNames, houseType);
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            //objDb.closeDbConn();
        }
        
        Collections.reverse(feedbackData);
        
        return feedbackData;
    }
    //deletes feedback data
    public void deleteFeedback(int feedbackId)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();
        
        int previousFeedbackId;
        int newFeedbackId;
        
        //Creates object of database access
        String[] columnName =
        {
            "HouseType", "FeedbackId", "Email", "Feedback"
        };
        Object[][] data = null;
        
        String dbQuery1 = "DELETE FROM HouseFeedback WHERE FeedbackId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, feedbackId);
            ps.setString(2, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error deleting data");
            se.printStackTrace(System.err);
        }
              
        try
        {
            //gets data
            data = objDb.to2dArray(objDb.getHouseData("HouseFeedback", columnName, houseType));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        try
        {
            for (int i=feedbackId; i<data.length; i++)
            {
                newFeedbackId = i;
                previousFeedbackId = i + 1;
                shuffleFeedback(previousFeedbackId++, newFeedbackId);
            }
        }
        catch(NullPointerException npe)
        {
            
        }
        
        //Closes connection
        objDb.closeDbConn();
    }
    //shuffles feedback ids
    public void shuffleFeedback(int previousFeedbackId, int newFeedbackId)
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "UPDATE HouseFeedback SET FeedbackId = ? WHERE FeedbackId = ? AND HouseType = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, newFeedbackId);
            ps.setInt(2, previousFeedbackId);
            ps.setString(3, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error updating data");
            se.printStackTrace(System.err);
        }
                   
        //Closes connection
        objDb.closeDbConn();
    }
    //obtain house members
    public ArrayList<ArrayList<String>> getHouseMembers()
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        String[] housesColumnName = {"Email", "MemberName", "HouseType"};
        ArrayList<ArrayList<String>> data = null;
        
        //Checks if there is data within the database
        try
        {
            data = objDb.getData("HouseMembers", housesColumnName);
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        return data;
    }
    //insert house members
    public boolean insertHouseMember()
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "INSERT INTO HouseMembers VALUES (?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, email);
            ps.setString(2, memberName);
            ps.setString(3, houseType);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
            
            return false;
        }
                   
        //Closes connection
        objDb.closeDbConn();
        
        return true;
    }
    //check house members
    public boolean checkDuplicateMember()
    {
        JavaSqlAccess objDb = new JavaSqlAccess("VillageHousesDb");
        
        Object[][] data = objDb.to2dArray(getHouseMembers());
        
        for (int i = 0; i<data.length; i++)
        {
            if ((data[i][0].toString()).equalsIgnoreCase(email))
            {
                return true;
            }
        }
        
        return false;
    }
    //delete house members
    public String deleteHouseMember()
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "DELETE FROM HouseMembers WHERE Email = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, email);
            
            int status = ps.executeUpdate();
            if (status <= 0)
            {
                return "member does not exist";
            }
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
            
            return "false";
        }
                   
        //Closes connection
        objDb.closeDbConn();
        
        return "true";
    }
    //obtain house leader
    public ArrayList<ArrayList<String>> getHouseLeaders()
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        String[] housesColumnName = {"Email", "MemberName", "HousePosition", "GradeLevel"};
        ArrayList<ArrayList<String>> data = null;
        
        //Checks if there is data within the database
        try
        {
            data = objDb.getData("Leadership", housesColumnName);
        }
        catch(IndexOutOfBoundsException ibe)
        {
            //Closes connection
            objDb.closeDbConn();
        }
        
        return data;
    }
    //insert house members
    public boolean insertHouseLeader()
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "INSERT INTO Leadership VALUES (?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, email);
            ps.setString(2, memberName);
            ps.setString(3, housePosition);
            ps.setInt(4, gradeLevel);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
            
            return false;
        }
                   
        //Closes connection
        objDb.closeDbConn();
        
        return true;
    }
    //check house members
    public boolean checkDuplicateLeader()
    {
        JavaSqlAccess objDb = new JavaSqlAccess("VillageHousesDb");
        
        Object[][] data = objDb.to2dArray(getHouseLeaders());
        
        for (int i = 0; i<data.length; i++)
        {
            if ((data[i][0].toString()).equalsIgnoreCase(email))
            {
                return true;
            }
        }
        
        return false;
    }
    //delete house members
    public String deleteHouseLeader()
    {
        //Creates object of database and connects to database
        JavaSqlAccess objDb = new JavaSqlAccess();
        objDb.setDbName("VillageHousesDb");
        objDb.setDbConn();
        Connection myDbConn = objDb.getDbConn();

        String dbQuery1 = "DELETE FROM Leadership WHERE Email = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, email);
            
            int status = ps.executeUpdate();
            if (status <= 0)
            {
                return "member does not exist";
            }
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
            
            return "false";
        }
                   
        //Closes connection
        objDb.closeDbConn();
        
        return "true";
    }
    public static void main(String[] args)
    {
        HouseManager houseManagerObj = new HouseManager();
        
        JavaSqlAccess objDb2 = new JavaSqlAccess("VillageHousesDb");
//        
//        houseManagerObj.setHouseType("Dragon");
//        
//        ArrayList<ArrayList<String>> pollAnswers = new ArrayList<>();
//        
//        Object[][] data = objDb2.to2dArray(houseManagerObj.getHouseMembers());
//        
//        for (int i = 0; i<data.length; i++)
//        {
//            for (int j=0; j<data[i].length; j++)
//            {
//                System.out.println(data[i][j]);
//            }
//        }

        houseManagerObj.setEmail("akakakaka");
        
        houseManagerObj.setHouseType("lol");
        
        houseManagerObj.setMemberName("xd");

        houseManagerObj.deleteHouseMember();
    }  
}
