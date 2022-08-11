/*
Omar Abouelazm
Period: 3

This class is the servlet and manages the navigation for the website
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        loadOnStartup = 1, 
        urlPatterns =
        {
            "/VillageHousesServlet", "/index", "/LogIn", "/ForgotPassword", "/AddProfile", "/VerifyAccount", "/AddAnnouncement", 
            "/HouseAnnouncements", "/RemoveAnnouncementPopUp", "/RemoveAnnouncement", "/HousePolls", "/AddPoll", "/RemovePollPopUp",
            "/RemovePoll", "/AddVote", "/RemoveVote", "/logInPopUp", "/signUpPopUp", "/forgotPasswordPopUp", "/verifyAccountPopUp", 
            "/addPollPopUp", "/announcementPopUp", "/SettingsPopUp", "/SaveSettings", "/Help", "/About", "/FeedbackPopUp", "/SendFeedback",
            "/Feedback", "/RemoveFeedbackPopUp", "/RemoveFeedback", "/AdminPage", "/AddMember", "/DeleteMember", "/AddLeader", 
            "/DeleteLeader"
        })
public class VillageHousesServlet extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VillageHousesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VillageHousesServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //session object to communicate data
        HttpSession sessionObj = request.getSession();
        
        sessionObj.setMaxInactiveInterval(10*60);
        
        LogInManager logInObj = new LogInManager();
        
        HouseManager houseManagerObj = new HouseManager();

        //Obtains path for where the user is requesting the data
        String userPath = request.getServletPath();

        String myUrl = "";
        
        String houseType;
        
        ArrayList<ArrayList<Object>> pollData;
        
        ArrayList<ArrayList<String>> announcementData;
        
        String userName = "";
        
        boolean houseTypeReset = false;

        //Checks User Path
        try
        {
            houseType = sessionObj.getAttribute("houseType").toString();
        }
        catch(NullPointerException npe)
        {
            houseTypeReset = true;
        }
        
        //Checks if the user's session has timed out
        if (houseTypeReset == true && !userPath.equals("/verifyAccountPopUp") && !userPath.equals("/index") 
                && !userPath.equals("/logInPopUp") && !userPath.equals("/forgotPasswordPopUp")
                && !userPath.equals("/signUpPopUp"))
        {
            houseManagerObj.print("Your Session Has Timed Out. Please Log In Again");
            
            sessionObj.setAttribute("errorDisplay", "Verify");
            
            sessionObj.setAttribute("bodyState","Hide");
            
            myUrl = "/index.jsp";
        }
        //Checks User Path
        else if (userPath.equals("/index"))
        {          
            sessionObj.setAttribute("logInDisplay", "");
            
            sessionObj.setAttribute("signUpDisplay", "");
            
            sessionObj.setAttribute("forgotPasswordDisplay", "");
            
            sessionObj.setAttribute("verifyAccountDisplay", "");
            
            sessionObj.setAttribute("errorDisplay", "");
            
            sessionObj.setAttribute("bodyState","");
            
            //Creates path
            myUrl = userPath + ".jsp";
        }
        else if (userPath.equals("/HousePolls"))
        {
            houseType = sessionObj.getAttribute("houseType").toString();
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString();
            houseManagerObj.setEmail(userName);
            
            pollData = houseManagerObj.getPolls();
            
            sessionObj.setAttribute("pollData", pollData);
   
            sessionObj.setAttribute("houseMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("houseMessageType", "");
            sessionObj.setAttribute("houseMessageResult", "");
            
            sessionObj.setAttribute("verifyDisplay", "");
            
            sessionObj.setAttribute("addPollDisplay", "");
            
            //Creates path
            myUrl = "/WEB-INF/HousePolls.jsp";   
        }
        else if (userPath.equals("/HouseAnnouncements"))
        {
            houseType = sessionObj.getAttribute("houseType").toString();
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString();
            houseManagerObj.setEmail(userName);
            
            //Obtains announcement data
            announcementData = houseManagerObj.getAnnouncementData();

            sessionObj.setAttribute("announcementData", announcementData);
       
            sessionObj.setAttribute("verifyDisplay", "");
            
            sessionObj.setAttribute("addAnnouncementDisplay", "");
            
            sessionObj.setAttribute("settingsDisplay", "");
            
            sessionObj.setAttribute("feedbackDisplay", "");
            
            sessionObj.setAttribute("houseMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("houseMessageType", "");
            sessionObj.setAttribute("houseMessageResult", "");
            
            //Creates path
            myUrl = "/WEB-INF/HouseAnnouncements.jsp";
        }
        else if (userPath.equals("/logInPopUp"))
        {
            //Displays log in pop up
            sessionObj.setAttribute("messageResult", "");

            sessionObj.setAttribute("messageDisplay", "nonAuthorized");
            
            sessionObj.setAttribute("logInDisplay", "Verify");
            
            sessionObj.setAttribute("bodyState", "Hide");
            
            //Creates path
            myUrl = "index.jsp";   
        }
        else if (userPath.equals("/signUpPopUp"))
        {
            //Displays sign up pop up
            sessionObj.setAttribute("messageResult", "");

            sessionObj.setAttribute("messageDisplay", "nonAuthorized");
            
            sessionObj.setAttribute("signUpDisplay", "Verify");
            
            sessionObj.setAttribute("bodyState", "Hide");
            
            //Creates path
            myUrl = "index.jsp";   
        }
        else if (userPath.equals("/forgotPasswordPopUp"))
        {
            //Displays forgot password pop up
            sessionObj.setAttribute("messageResult", "");

            sessionObj.setAttribute("messageDisplay", "nonAuthorized");
            
            sessionObj.setAttribute("forgotPasswordDisplay", "Verify");
            
            sessionObj.setAttribute("logInDisplay", "");
            
            sessionObj.setAttribute("signUpDisplay", "");
            
            sessionObj.setAttribute("bodyState", "Hide");
            
            //Creates path
            myUrl = "index.jsp";   
        }
        else if (userPath.equals("/verifyAccountPopUp"))
        {
            //Displays verify account pop up
            sessionObj.setAttribute("messageResult", "");

            sessionObj.setAttribute("messageDisplay", "nonAuthorized");
            
            sessionObj.setAttribute("verifyAccountDisplay", "Verify");
            
            sessionObj.setAttribute("bodyState", "Hide");
            
            //Creates path
            myUrl = "index.jsp";   
        }
        else if (userPath.equals("/addPollPopUp"))
        {
            //Displays add poll pop up
            sessionObj.setAttribute("houseMessageResult", "");

            sessionObj.setAttribute("houseMessageDisplay", "nonAuthorized");
            
            sessionObj.setAttribute("addPollDisplay", "Verify");
            
            //Creates path
            myUrl = "/WEB-INF/HousePolls.jsp";   
        }
        else if (userPath.equals("/announcementPopUp"))
        {
            //Displays announcement pop up
            sessionObj.setAttribute("houseMessageResult", "");

            sessionObj.setAttribute("houseMessageDisplay", "nonAuthorized");
            
            sessionObj.setAttribute("addAnnouncementDisplay", "Verify");
            
            //Creates path
            myUrl = "/WEB-INF/HouseAnnouncements.jsp";   
        }
        else if (userPath.equals("/SettingsPopUp"))
        {
            //Displays settings pop up
            userName = (sessionObj.getAttribute("userName")).toString();
            
            houseManagerObj.setEmail(userName);
            
            sessionObj.setAttribute("settingsDisplay", "Verify");
            
            sessionObj.setAttribute("notificationSetting", houseManagerObj.findNotificationType());
            
            //Creates path
            myUrl = "/WEB-INF/HouseAnnouncements.jsp";   
        }
        else if (userPath.equals("/About"))
        {           
            userName = (sessionObj.getAttribute("userName")).toString();
            
            houseManagerObj.setEmail(userName);
            
            houseType = (sessionObj.getAttribute("houseType")).toString();
            
            houseManagerObj.setHouseType(houseType);
            
            sessionObj.setAttribute("leadershipData", houseManagerObj.getLeadershipData());
            
            //Creates path
            myUrl = "WEB-INF/About.jsp";
        }
        else if (userPath.equals("/Help"))
        {           
            //Creates path
            myUrl = "WEB-INF/Help.jsp";
        }
        else if (userPath.equals("/FeedbackPopUp"))
        {        
            sessionObj.setAttribute("messageResult", "");

            sessionObj.setAttribute("messageDisplay", "nonAuthorized");
            
            sessionObj.setAttribute("feedbackDisplay", "Verify");
                       
            //Creates path
            myUrl = "/WEB-INF/HouseAnnouncements.jsp"; 
        }
        else if (userPath.equals("/Feedback"))
        {
            houseType = (sessionObj.getAttribute("houseType")).toString();
            
            houseManagerObj.setHouseType(houseType);
            
            sessionObj.setAttribute("feedbackData", houseManagerObj.getFeedbackData());
            
            sessionObj.setAttribute("verifyDisplay", "");
            
            //Creates path
            myUrl = "/WEB-INF/Feedback.jsp"; 
        }
        
        try
        {            
            //redirects back to the new URL with new data, servlet to jsp
            request.getRequestDispatcher(myUrl).forward(request, response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //session object to communicate data
        HttpSession sessionObj = request.getSession();
        sessionObj.setMaxInactiveInterval(10*60);
        
        LogInManager logInObj = new LogInManager();
        
        HouseManager houseManagerObj = new HouseManager();

        //Obtains path for where the user is requesting the data
        String userPath = request.getServletPath();
        
        ArrayList<ArrayList<String>> announcementData;
                
        ArrayList<ArrayList<Object>> pollData;
        
        ArrayList<ArrayList<String>> feedbackData;
        
        ArrayList<ArrayList<String>> houseMembersData;
        
        String userName = "";
        String houseType = "";

        String myUrl = "";    
        
        boolean houseTypeReset = false;
        
        //Checks User Path
        try
        {
            houseType = sessionObj.getAttribute("houseType").toString();
        }
        catch(NullPointerException npe)
        {
            houseTypeReset = true;
        }
        
        //Checks if user timed out
        if (houseTypeReset == true && !userPath.equals("/LogIn") && !userPath.equals("/index") 
                && !userPath.equals("/ForgotPassword") && !userPath.equals("/AddProfile") 
                && !userPath.equals("/VerifyAccount") && !userPath.equals("/AdminPage") &&
                !userPath.equals("/AddMember") && !userPath.equals("/DeleteMember") && 
                !userPath.equals("/AddLeader") && !userPath.equals("/DeleteLeader"))
        {
            houseManagerObj.print("Your Session Has Timed Out. Please Log In Again");
            
            sessionObj.setAttribute("errorDisplay", "Verify");
            
            sessionObj.setAttribute("bodyState","Hide");
            
            myUrl = "/index.jsp";
        }
        else if (userPath.equals("/index"))
        {
            sessionObj.setAttribute("errorDisplay", "");
            
            //Creates path
            myUrl = userPath + ".jsp";
        }
        else if (userPath.equals("/HouseAnnouncements"))
        {
            houseType = sessionObj.getAttribute("houseType").toString();
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString();
            houseManagerObj.setEmail(userName);
            
            announcementData = houseManagerObj.getAnnouncementData();

            sessionObj.setAttribute("announcementData", announcementData);
             
            sessionObj.setAttribute("verifyDisplay", "");
            
            sessionObj.setAttribute("addAnnouncementDisplay", "");

            //Creates path
            myUrl = "/WEB-INF/HouseAnnouncements.jsp";
        } 
        else if (userPath.equals("/LogIn"))
        {
            logInObj.setEmail(request.getParameter("userName"));
            logInObj.setPassword(request.getParameter("password"));

            //Checks if user's log in is correct
            if (logInObj.checkLogIn() == true)
            {                      
                sessionObj.setAttribute("logInDisplay", "");

                sessionObj.setAttribute("bodyState", "");

                sessionObj.setAttribute("houseMessageDisplay", "nonAuthorized");
                sessionObj.setAttribute("houseMessageType", "");
                sessionObj.setAttribute("houseMessageResult", "");
                    
                if ((logInObj.getEmail()).equalsIgnoreCase("admin"))
                {        
                    sessionObj.setAttribute("addMemberMessageDisplay", "nonAuthorized");
                    sessionObj.setAttribute("addMemberMessageType", "");
                    sessionObj.setAttribute("addMemberMessageResult", "");
                    
                    sessionObj.setAttribute("deleteMemberMessageDisplay", "nonAuthorized");
                    sessionObj.setAttribute("deleteMemberMessageType", "");
                    sessionObj.setAttribute("deleteMemberMessageResult", "");
                    
                    sessionObj.setAttribute("addLeaderMessageDisplay", "nonAuthorized");
                    sessionObj.setAttribute("addLeaderMessageType", "");
                    sessionObj.setAttribute("addLeaderMessageResult", "");
                    
                    sessionObj.setAttribute("deleteLeaderMessageDisplay", "nonAuthorized");
                    sessionObj.setAttribute("deleteLeaderMessageType", "");
                    sessionObj.setAttribute("deleteLeaderMessageResult", "");
            
                    //Creates path
                    myUrl = "AdminPage";
                }
                else
                {
                    userName = request.getParameter("userName");
                    sessionObj.setAttribute("userName", userName);

                    houseManagerObj.setEmail(userName);

                    houseType = houseManagerObj.findHouseType();
                    sessionObj.setAttribute("houseType", houseType);

                    sessionObj.setAttribute("lowerHouseType", houseType.toLowerCase());

                    houseManagerObj.setHouseType(houseType);
                    announcementData = houseManagerObj.getAnnouncementData();

                    sessionObj.setAttribute("announcementData", announcementData);

                    String authorization = houseManagerObj.findAuthorization();
                    sessionObj.setAttribute("authorization", authorization);

                    //Creates path
                    myUrl = "/WEB-INF/HouseAnnouncements.jsp";
                }
            }
            //Checks if user's account is not verified
            else if (logInObj.checkAccountVerification() == true)
            {
                sessionObj.setAttribute("messageResult", "Your Account is Not Verified. Check Your Email for the Verification Code");     
                            
                sessionObj.setAttribute("messageDisplay", "authorized");
                
                //Creates path
                myUrl = "/index.jsp";
            }
            else
            {
                sessionObj.setAttribute("messageResult", "Invalid Username or Password");
                                
                sessionObj.setAttribute("messageDisplay", "authorized");

                //Creates path
                myUrl = "/index.jsp";
            }
        }
        else if (userPath.equals("/ForgotPassword"))
        {
            String email = request.getParameter("email");
            
            logInObj.setEmail(email);
            
            //checks if an email is valid
            if (logInObj.checkValidEmail() == false)
            {
                sessionObj.setAttribute("messageResult", "Your Email is Not Valid");
                
                sessionObj.setAttribute("messageType", "error");
            }
            //checks if it is a registered email
            else if(logInObj.checkSignUp() == false)
            {
                sessionObj.setAttribute("messageResult", "Your Email is Not Registered");
                
                sessionObj.setAttribute("messageType", "error");
            }
            else
            {
                logInObj.setPassword(logInObj.getRegisteredPassword());
                logInObj.sendEmail("Forgot Password?", "Here is your forgotten password: " + logInObj.getPassword());
                       
                sessionObj.setAttribute("messageResult", "Your Password Has Been Sent Via Email");
                
                sessionObj.setAttribute("messageType", "success");
            }
            
            sessionObj.setAttribute("messageDisplay", "authorized");
                         
            //Creates path
            myUrl = "/index.jsp";
        }
        else if (userPath.equals("/AddProfile"))
        {
            //sets new username and password
            logInObj.setEmail(request.getParameter("email"));
            logInObj.setPassword(request.getParameter("firstPassword"));

            //checks if an email is valid
            if (logInObj.checkValidEmail() == false)
            {
                sessionObj.setAttribute("messageResult", "Your Email is Not Valid");
                
                sessionObj.setAttribute("messageType", "error");
            }
            //Checks if the passwords match
            else if (!(request.getParameter("firstPassword")).equals(request.getParameter("secondPassword")))
            {
                sessionObj.setAttribute("messageResult", "Your Passwords Do Not Match");
                
                sessionObj.setAttribute("messageType", "error");
            }
            //Checks if the user entered values within the username and password fields
            else if ((logInObj.getEmail()).equals("")
                    || (logInObj.getPassword()).equals(""))
            {
                sessionObj.setAttribute("messageResult", "Please Enter Values Within the Fields");
                
                sessionObj.setAttribute("messageType", "error");            
            }
            //Checks if the user entered a preexisting username
            else if (logInObj.checkSignUp() == true)
            {
                sessionObj.setAttribute("messageResult", "You Have Entered a Duplicate Email");
                
                sessionObj.setAttribute("messageType", "error");
            }
            else if (logInObj.checkHouseRegistration() == false)
            {
                sessionObj.setAttribute("messageResult", "Your Email is Not Recognized As Apart of Our House Database. "
                        + "Please Contact Admin if You Think this is Incorrect And Sign Up Again");
                
                sessionObj.setAttribute("messageType", "error");
            }
            //Sends verification code to user 
            else
            {
                logInObj.sendVerificationCode();
                
                sessionObj.setAttribute("email", logInObj.getEmail());
                
                sessionObj.setAttribute("messageResult", "A Verification Code Has Been Sent to Your Email");
                
                sessionObj.setAttribute("messageType", "success");
                
                sessionObj.setAttribute("verifyAccountDisplay", "Verify");
            
                sessionObj.setAttribute("bodyState", "Hide");
                
                sessionObj.setAttribute("signUpDisplay", "");
            }
            
            sessionObj.setAttribute("messageDisplay", "authorized");
            
            //Creates path
            myUrl = "/index.jsp";
        }
        else if (userPath.equals("/VerifyAccount"))
        {          
            logInObj.setEmail(sessionObj.getAttribute("email").toString());
            
            String verificationCode = request.getParameter("verificationCode");
            
            //Checks if the verification code the user entered was valid
            if (logInObj.checkVerificationCode(verificationCode) == false)
            {
                sessionObj.setAttribute("messageResult", "You Have Entered an Incorrect Verification Code");
                
                sessionObj.setAttribute("messageType", "error");
                            
                sessionObj.setAttribute("messageDisplay", "authorized");

                //Creates path
                myUrl = "/index.jsp";
            }
            else
            {
                boolean execution = logInObj.addProfile();
                
                if (execution == false)
                {
                    sessionObj.setAttribute("messageResult", "Something went wrong. Please sign up again and avoid entering special characters "
                            + "or long passwords/emails");
                
                    sessionObj.setAttribute("messageType", "error");
                            
                    sessionObj.setAttribute("messageDisplay", "authorized");
                }
                else
                {
                    String email = logInObj.getEmail();

                    logInObj.deleteVerification();

                    sessionObj.setAttribute("userName", email);

                    houseManagerObj.setEmail(email);

                    sessionObj.setAttribute("houseType", houseManagerObj.findHouseType());

                    myUrl = "HouseAnnouncements";

                    sessionObj.setAttribute("verifyAccountDisplay", "");

                    sessionObj.setAttribute("bodyState", "");
                }
            }
        }
        else if (userPath.equals("/RemoveAnnouncementPopUp"))
        {
            sessionObj.setAttribute("verifyDisplay", "Verify");
            
            houseType = sessionObj.getAttribute("houseType").toString();
            
            houseManagerObj.setHouseType(houseType);
            
            announcementData = houseManagerObj.getAnnouncementData();
            
            //Displays announcement title for a specific id
            for (int i=0; i<announcementData.size(); i++)
            {
                String id = "removeAnnouncement" + i;
                if (request.getParameter(id) != null)
                {
                    String announcementTitle = houseManagerObj.obtainAnnouncementTitle(i);
            
                    sessionObj.setAttribute("announcementId", i);
                    sessionObj.setAttribute("announcementTitle", announcementTitle);
                    
                    break;
                }
            }
            
            //Creates path
            myUrl = "/WEB-INF/HouseAnnouncements.jsp";
        }
        else if (userPath.equals("/AddAnnouncement"))
        {        
            houseType = (sessionObj.getAttribute("houseType")).toString();
            houseManagerObj.setHouseType(houseType);
            
            String announcementTitle = request.getParameter("announcementTitle");
                  
            String announcementDescription = request.getParameter("announcementDescription");
            
            //Checks if the announcement title is empty
            if (!announcementTitle.equals(""))
            {
                int announcementId = houseManagerObj.obtainAnnouncementNumber();

                boolean insertedData = houseManagerObj.insertAnnouncementData(announcementId, 
                        announcementTitle, announcementDescription);

                announcementData = houseManagerObj.getAnnouncementData();

                sessionObj.setAttribute("announcementData", announcementData);

                //Checks if the data inserted correctly
                if (insertedData == true)
                {
                    sessionObj.setAttribute("houseMessageResult", "You Have Successfully Added An Announcement!");

                    sessionObj.setAttribute("houseMessageType", "success"); 
                }
                else
                {
                    sessionObj.setAttribute("houseMessageResult", "There Was An Error with Adding the Announcement");      

                    sessionObj.setAttribute("houseMessageType", "error"); 
                }          
            }
            else
            {
                sessionObj.setAttribute("houseMessageResult", "Please Enter An Announcement Title");      

                sessionObj.setAttribute("houseMessageType", "error"); 
            }
            
            sessionObj.setAttribute("houseMessageDisplay", "authorized"); 
            
            myUrl = "/WEB-INF/HouseAnnouncements.jsp";     
        }
        else if (userPath.equals("/RemoveAnnouncement"))
        {
            int announcementId = Integer.parseInt(sessionObj.getAttribute("announcementId").toString());
            
            houseType = sessionObj.getAttribute("houseType").toString();

            houseManagerObj.setHouseType(houseType);
            
            //deletes announcement
            houseManagerObj.deleteAnnouncement(announcementId);
            
            //Creates path
            myUrl = "HouseAnnouncements";     
        }
        else if (userPath.equals("/HousePolls"))
        {
            houseType = sessionObj.getAttribute("houseType").toString();
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString();
            houseManagerObj.setEmail(userName);
            
            //Obtains poll data
            pollData = houseManagerObj.getPolls();
            
            sessionObj.setAttribute("pollData", pollData);

            sessionObj.setAttribute("verifyDisplay", "");
            
            sessionObj.setAttribute("addPollDisplay", "");
            
            //Creates path
            myUrl = "/WEB-INF/HousePolls.jsp";   
        }
        else if (userPath.equals("/AddPoll"))
        {
            houseType = (sessionObj.getAttribute("houseType")).toString();
            houseManagerObj.setHouseType(houseType);
            
            userName = (sessionObj.getAttribute("userName")).toString();
            houseManagerObj.setEmail(userName);
            
            ArrayList<String> pollAnswers = new ArrayList<>();
            
            String answer1 = request.getParameter("answer1Field");
            String answer2 = request.getParameter("answer2Field");
            String answer3 = request.getParameter("answer3Field");
            String answer4 = request.getParameter("answer4Field");
            String answer5 = request.getParameter("answer5Field");
            
            boolean duplicate = false;
            
            //Checks if at least two answer choices are filled out
            if (!answer1.equals(""))
            {
                pollAnswers.add(answer1);
                
                if (answer1.equalsIgnoreCase(answer2) || 
                        answer1.equalsIgnoreCase(answer3) || 
                        answer1.equalsIgnoreCase(answer4) || 
                        answer1.equalsIgnoreCase(answer5))
                {
                    duplicate = true;
                }
            }
            if (!answer2.equals(""))
            {
                pollAnswers.add(answer2);
                
                if (answer2.equalsIgnoreCase(answer1) || 
                        answer2.equalsIgnoreCase(answer3) || 
                        answer2.equalsIgnoreCase(answer4) || 
                        answer2.equalsIgnoreCase(answer5))
                {
                    duplicate = true;
                }
            }
            if (!answer3.equals(""))
            {
                pollAnswers.add(answer3);
                
                if (answer3.equalsIgnoreCase(answer1) || 
                        answer3.equalsIgnoreCase(answer2) || 
                        answer3.equalsIgnoreCase(answer4) || 
                        answer3.equalsIgnoreCase(answer5))
                {
                    duplicate = true;
                }
            }
            if (!answer4.equals(""))
            {              
                pollAnswers.add(answer4);
                
                if (answer4.equalsIgnoreCase(answer1) || 
                        answer4.equalsIgnoreCase(answer2) || 
                        answer4.equalsIgnoreCase(answer3) || 
                        answer4.equalsIgnoreCase(answer5))
                {
                    duplicate = true;
                }
            }
            if (!answer5.equals(""))
            {
                pollAnswers.add(answer5);
                
                if (answer5.equalsIgnoreCase(answer1) || 
                    answer5.equalsIgnoreCase(answer2) || 
                    answer5.equalsIgnoreCase(answer3) || 
                    answer5.equalsIgnoreCase(answer4))
                {
                    duplicate = true;
                }
            }
            
            //Checks if the question field is filled out
            if ((request.getParameter("questionField")).equals(""))
            {
                sessionObj.setAttribute("houseMessageResult", "You Need to Enter a Question");
                
                sessionObj.setAttribute("houseMessageType", "error");
            }
            //Checks if the user entered duplicate answer choices
            else if (duplicate == true)
            {
                sessionObj.setAttribute("houseMessageResult", "You Have Entered Duplicate Answer Choices");
                
                sessionObj.setAttribute("houseMessageType", "error"); 
            }
            //Checks if the user put less than 2 answer choices
            else if (pollAnswers.size() < 2)
            {
                sessionObj.setAttribute("houseMessageResult", "You Must Fill Out at Least Two Answer Choices");
                
                sessionObj.setAttribute("houseMessageType", "error"); 
            }
            else
            {
                int pollId = houseManagerObj.obtainPollNumber();

                boolean execution = houseManagerObj.insertNewPoll(pollId, request.getParameter("questionField"), 
                        pollAnswers);
                
                if (execution == false)
                {
                    sessionObj.setAttribute("houseMessageResult", "You have entered too many characters, "
                            + "or special characters. Please enter shorter values and avoid special characters.");
                
                    sessionObj.setAttribute("houseMessageType", "error"); 
                }
                else
                {
                    pollData = houseManagerObj.getPolls();

                    sessionObj.setAttribute("pollData", pollData);

                    sessionObj.setAttribute("houseMessageResult", "You Have Successfully Added A Poll!"); 

                    sessionObj.setAttribute("houseMessageType", "success"); 
                }
            }
            
            sessionObj.setAttribute("houseMessageDisplay", "authorized"); 
            
            myUrl = "/WEB-INF/HousePolls.jsp";    
        }
        else if (userPath.equals("/RemovePollPopUp"))
        {
            sessionObj.setAttribute("verifyDisplay", "Verify");
            
            houseType = sessionObj.getAttribute("houseType").toString();
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString();
            houseManagerObj.setEmail(userName);
            
            pollData = houseManagerObj.getPolls();
            
            //Displays poll question for specific id
            for (int i=0; i<pollData.size(); i++)
            {
                String id = "removePoll" + i;
                if (request.getParameter(id) != null)
                {
                    String pollQuestion = houseManagerObj.obtainPollQuestion(i);
            
                    sessionObj.setAttribute("pollId", i);
                    sessionObj.setAttribute("pollQuestion", pollQuestion);
                    
                    break;
                }
            }
            
            //Creates path
            myUrl = "/WEB-INF/HousePolls.jsp";
        }
        else if (userPath.equals("/RemovePoll"))
        {
            int pollId = Integer.parseInt(sessionObj.getAttribute("pollId").toString());
            
            houseType = sessionObj.getAttribute("houseType").toString();

            houseManagerObj.setHouseType(houseType);
            
            houseManagerObj.deletePoll(pollId);
            
            //Creates path
            myUrl = "HousePolls";     
        }
        else if (userPath.equals("/AddVote"))
        {
            int pollId = -1;
            
            String pollAnswer;
            
            houseType = sessionObj.getAttribute("houseType").toString();  
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString(); 
            houseManagerObj.setEmail(userName);
            
            pollData = houseManagerObj.getPolls();
            
            //Obtains the poll id that was voted on
            for (int i=0; i<pollData.size(); i++)
            {
                String id = "votePoll" + i;
                if (request.getParameter(id) != null)
                {
                    pollId = i;
                    
                    break;
                }
            }
            
            //Inserts their vote/poll answer
            if (pollId >= 0)
            {
                pollAnswer = request.getParameter("pollAnswer" + pollId);
                
                if (pollAnswer == null)
                {
                    
                }
                else
                {
                    houseManagerObj.insertPollAnswer(pollId, pollAnswer);
                }
            }
            
            //Creates path
            myUrl = "HousePolls";  
        }
        else if (userPath.equals("/RemoveVote"))
        {
            int pollId = -1;
            
            houseType = sessionObj.getAttribute("houseType").toString();  
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString(); 
            houseManagerObj.setEmail(userName);
            
            pollData = houseManagerObj.getPolls();
            
            //Changes vote for a specific poll and user
            for (int i=0; i<pollData.size(); i++)
            {
                String id = "changeVotePoll" + i;
                if (request.getParameter(id) != null)
                {
                    pollId = i;
                    
                    break;
                }
            }
            
            if (pollId >= 0)
            {                           
                houseManagerObj.removeAnsweredPoll(pollId);
            }
            
            //Creates path
            myUrl = "HousePolls";
        }
        else if (userPath.equals("/SaveSettings"))
        {
            houseType = sessionObj.getAttribute("houseType").toString();  
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString(); 
            houseManagerObj.setEmail(userName);
            
            String notificationType = request.getParameter("notificationTypes");
            
            //Saves notification settings
            houseManagerObj.saveNotifications(notificationType);
            
            sessionObj.setAttribute("houseMessageDisplay", "authorized");
            sessionObj.setAttribute("houseMessageType", "success");
            sessionObj.setAttribute("houseMessageResult", "Your Changes Have Been Saved");
            
            sessionObj.setAttribute("notificationSetting", houseManagerObj.findNotificationType());
            
            //Creates path
            myUrl = "HouseAnnouncements";
        }
        else if (userPath.equals("/SendFeedback"))
        {
            houseType = sessionObj.getAttribute("houseType").toString();  
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString(); 
            houseManagerObj.setEmail(userName);
                  
            //Obtains feedback id
            int feedbackId = houseManagerObj.obtainFeedbackNumber();
            
            String feedback = request.getParameter("feedback");
            
            //Adds feedback to database
            boolean execution = houseManagerObj.insertFeedback(feedbackId, feedback);
            
            if (execution == false)
            {
                sessionObj.setAttribute("houseMessageDisplay", "authorized");
                sessionObj.setAttribute("houseMessageType", "error");
                sessionObj.setAttribute("houseMessageResult", "You have entered too many characters, "
                            + "or special characters. Please enter shorter values and avoid special characters.");
            }
            else
            {
                sessionObj.setAttribute("houseMessageDisplay", "authorized");
                sessionObj.setAttribute("houseMessageType", "success");
                sessionObj.setAttribute("houseMessageResult", "Your Feedback Has Been Sent");
            }
            
            //Creates path
            myUrl = "HouseAnnouncements";
        }
        else if (userPath.equals("/RemoveFeedbackPopUp"))
        {
            sessionObj.setAttribute("verifyDisplay", "Verify");
            
            houseType = sessionObj.getAttribute("houseType").toString();
            houseManagerObj.setHouseType(houseType);
            
            userName = sessionObj.getAttribute("userName").toString();
            houseManagerObj.setEmail(userName);
            
            feedbackData = houseManagerObj.getFeedbackData();
                      
            //Obtains feedback id
            for (int i=0; i<feedbackData.size(); i++)
            {
                String id = "removeFeedback" + i;
                if (request.getParameter(id) != null)
                {
                    sessionObj.setAttribute("feedbackId", i);
                    
                    break;
                }
            }
            
            //Creates path
            myUrl = "/WEB-INF/Feedback.jsp";
        }
        else if (userPath.equals("/RemoveFeedback"))
        {
            int feedbackId = Integer.parseInt(sessionObj.getAttribute("feedbackId").toString());
            
            houseType = sessionObj.getAttribute("houseType").toString();

            houseManagerObj.setHouseType(houseType);
            
            //Removes feedback with specified id
            houseManagerObj.deleteFeedback(feedbackId);
            
            sessionObj.setAttribute("verifyDisplay", "");
            
            sessionObj.setAttribute("feedbackData", houseManagerObj.getFeedbackData());
            
            //Creates path
            myUrl = "/WEB-INF/Feedback.jsp";
        }
        else if (userPath.equals("/AdminPage"))
        {
            houseMembersData = houseManagerObj.getHouseMembers();
            
            sessionObj.setAttribute("houseMembersData", houseMembersData);
            
            houseMembersData = houseManagerObj.getHouseLeaders();
            
            sessionObj.setAttribute("houseLeadersData", houseMembersData);
            
            //Creates path
            myUrl = "/WEB-INF/AdminPage.jsp";
        }
        else if (userPath.equals("/AddMember"))
        {
            houseManagerObj.setEmail(request.getParameter("memberEmail"));
            
            houseManagerObj.setMemberName(request.getParameter("memberName"));
            
            houseManagerObj.setHouseType(request.getParameter("houseType"));
            
            logInObj.setEmail(request.getParameter("memberEmail"));
            
            boolean execution;
            
            if ((houseManagerObj.getEmail()).equals(""))
            {
                sessionObj.setAttribute("addMemberMessageDisplay", "authorized");
                sessionObj.setAttribute("addMemberMessageType", "error");
                sessionObj.setAttribute("addMemberMessageResult", "Please enter an email");
            }
            else if (houseManagerObj.checkDuplicateMember() == true)
            {
                sessionObj.setAttribute("addMemberMessageDisplay", "authorized");
                sessionObj.setAttribute("addMemberMessageType", "error");
                sessionObj.setAttribute("addMemberMessageResult", "You have entered a duplicate email");
            }
            else if (logInObj.checkValidEmail() == false)
            {
                sessionObj.setAttribute("addMemberMessageDisplay", "authorized");
                sessionObj.setAttribute("addMemberMessageType", "error");
                sessionObj.setAttribute("addMemberMessageResult", "You have entered an invalid email");
            }
            else
            {
                execution = houseManagerObj.insertHouseMember();
            
                if (execution == false)
                {
                    sessionObj.setAttribute("addMemberMessageDisplay", "authorized");
                    sessionObj.setAttribute("addMemberMessageType", "error");
                    sessionObj.setAttribute("addMemberMessageResult", "You have entered too many characters, "
                            + "or special characters. Please enter shorter values and avoid special characters.");
                }
                else 
                {
                    sessionObj.setAttribute("addMemberMessageDisplay", "authorized");
                    sessionObj.setAttribute("addMemberMessageType", "success");
                    sessionObj.setAttribute("addMemberMessageResult", "You have successfully added the member");
                }
            }
            
            sessionObj.setAttribute("deleteMemberMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("deleteMemberMessageType", "");
            sessionObj.setAttribute("deleteMemberMessageResult", "");
            
            sessionObj.setAttribute("deleteLeaderMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("deleteLeaderMessageType", "");
            sessionObj.setAttribute("deleteLeaderMessageResult", "");
            
            sessionObj.setAttribute("addLeaderMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("addLeaderMessageType", "");
            sessionObj.setAttribute("addLeaderMessageResult", "");
            
            //Creates path
            myUrl = "AdminPage";
        }
        else if (userPath.equals("/DeleteMember"))
        {           
            houseManagerObj.setEmail(request.getParameter("deleteMemberEmail"));
            
            String execution;
            
            execution = houseManagerObj.deleteHouseMember();
            
            if (execution.equals("false"))
            {
                sessionObj.setAttribute("deleteMemberMessageDisplay", "authorized");
                sessionObj.setAttribute("deleteMemberMessageType", "error");
                sessionObj.setAttribute("deleteMemberMessageResult", "You have entered too many characters, "
                        + "or special characters. Please enter a shorter email and avoid special characters.");
            }
            else if (execution.equals("member does not exist"))
            {
                sessionObj.setAttribute("deleteMemberMessageDisplay", "authorized");
                sessionObj.setAttribute("deleteMemberMessageType", "error");
                sessionObj.setAttribute("deleteMemberMessageResult", "Warning: The member does not exist");
            }
            else 
            {
                sessionObj.setAttribute("deleteMemberMessageDisplay", "authorized");
                sessionObj.setAttribute("deleteMemberMessageType", "success");
                sessionObj.setAttribute("deleteMemberMessageResult", "You have successfully deleted the member");
            }
            
            sessionObj.setAttribute("addMemberMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("addMemberMessageType", "");
            sessionObj.setAttribute("addMemberMessageResult", "");
            
            sessionObj.setAttribute("deleteLeaderMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("deleteLeaderMessageType", "");
            sessionObj.setAttribute("deleteLeaderMessageResult", "");
            
            sessionObj.setAttribute("addLeaderMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("addLeaderMessageType", "");
            sessionObj.setAttribute("addLeaderMessageResult", "");
            
            //Creates path
            myUrl = "AdminPage";
        }
        else if (userPath.equals("/AddLeader"))
        {
            houseManagerObj.setEmail(request.getParameter("leaderEmail"));
            
            houseManagerObj.setMemberName(request.getParameter("leaderName"));
            
            houseManagerObj.setHousePosition(request.getParameter("housePosition"));
            
            try
            {
                houseManagerObj.setGradeLevel(Integer.parseInt(request.getParameter("gradeLevel")));
                
                logInObj.setEmail(request.getParameter("leaderEmail"));

                boolean execution;

                if ((houseManagerObj.getEmail()).equals(""))
                {
                    sessionObj.setAttribute("addLeaderMessageDisplay", "authorized");
                    sessionObj.setAttribute("addLeaderMessageType", "error");
                    sessionObj.setAttribute("addLeaderMessageResult", "Please enter an email");
                }
                else if (houseManagerObj.checkDuplicateLeader() == true)
                {
                    sessionObj.setAttribute("addLeaderMessageDisplay", "authorized");
                    sessionObj.setAttribute("addLeaderMessageType", "error");
                    sessionObj.setAttribute("addLeaderMessageResult", "You have entered a duplicate email");
                }
                else if (logInObj.checkValidEmail() == false)
                {
                    sessionObj.setAttribute("addLeaderMessageDisplay", "authorized");
                    sessionObj.setAttribute("addLeaderMessageType", "error");
                    sessionObj.setAttribute("addLeaderMessageResult", "You have entered an invalid email");
                }
                else
                {
                    execution = houseManagerObj.insertHouseLeader();

                    if (execution == false)
                    {
                        sessionObj.setAttribute("addLeaderMessageDisplay", "authorized");
                        sessionObj.setAttribute("addLeaderMessageType", "error");
                        sessionObj.setAttribute("addLeaderMessageResult", "You have entered too many characters, "
                                + "or special characters. Please enter shorter values and avoid special characters.");
                    }
                    else 
                    {
                        sessionObj.setAttribute("addLeaderMessageDisplay", "authorized");
                        sessionObj.setAttribute("addLeaderMessageType", "success");
                        sessionObj.setAttribute("addLeaderMessageResult", "You have successfully added the member");
                    }
                }
            }
            catch(NumberFormatException nfe)
            {
                sessionObj.setAttribute("addLeaderMessageDisplay", "authorized");
                sessionObj.setAttribute("addLeaderMessageType", "error");
                sessionObj.setAttribute("addLeaderMessageResult", "Please enter a number for the grade level");
            }
            
            sessionObj.setAttribute("addMemberMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("addMemberMessageType", "");
            sessionObj.setAttribute("addMemberMessageResult", "");
            
            sessionObj.setAttribute("deleteMemberMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("deleteMemberMessageType", "");
            sessionObj.setAttribute("deleteMemberMessageResult", "");
            
            sessionObj.setAttribute("deleteLeaderMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("deleteLeaderMessageType", "");
            sessionObj.setAttribute("deleteLeaderMessageResult", "");
            
            //Creates path
            myUrl = "AdminPage";
        }
        else if (userPath.equals("/DeleteLeader"))
        {           
            houseManagerObj.setEmail(request.getParameter("deleteLeaderEmail"));
            
            String execution;
            
            execution = houseManagerObj.deleteHouseLeader();
            
            if (execution.equals("false"))
            {
                sessionObj.setAttribute("deleteLeaderMessageDisplay", "authorized");
                sessionObj.setAttribute("deleteLeaderMessageType", "error");
                sessionObj.setAttribute("deleteLeaderMessageResult", "You have entered too many characters, "
                        + "or special characters. Please enter a shorter email and avoid special characters.");
            }
            else if (execution.equals("member does not exist"))
            {
                sessionObj.setAttribute("deleteLeaderMessageDisplay", "authorized");
                sessionObj.setAttribute("deleteLeaderMessageType", "error");
                sessionObj.setAttribute("deleteLeaderMessageResult", "Warning: The member does not exist");
            }
            else 
            {
                sessionObj.setAttribute("deleteLeaderMessageDisplay", "authorized");
                sessionObj.setAttribute("deleteLeaderMessageType", "success");
                sessionObj.setAttribute("deleteLeaderMessageResult", "You have successfully deleted the member");
            }
            
            sessionObj.setAttribute("addLeaderMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("addLeaderMessageType", "");
            sessionObj.setAttribute("addLeaderMessageResult", "");
            
            sessionObj.setAttribute("addMemberMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("addMemberMessageType", "");
            sessionObj.setAttribute("addMemberMessageResult", "");
            
            sessionObj.setAttribute("deleteMemberMessageDisplay", "nonAuthorized");
            sessionObj.setAttribute("deleteMemberMessageType", "");
            sessionObj.setAttribute("deleteMemberMessageResult", "");
            
            //Creates path
            myUrl = "AdminPage";
        }
        try
        {
            //redirects back to the new URL with new data, servlet to jsp
            request.getRequestDispatcher(myUrl).forward(request, response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
