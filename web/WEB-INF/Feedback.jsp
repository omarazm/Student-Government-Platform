<!-- 
Omar Abouelazm
Period: 3
-->
<!--This is the feedback page of the website. It allows for 
an admin to obtain information on feedback people send
about their respective house-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
        <link href="CSS/VillageHousesCss.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <!-- Creates the header/navigation bar-->
        <div id = "houseHeader">      
            
                <!-- Creates the brand link/logo-->             
                <a href="About" class="headerButtons linkHeaderButton linkLogo"> 
                    <img id = "villageIconImage" src="Images/TheVillageSchoolLogo.png" alt="Village Icon" 
                        title="Village Logo"> 
                    <span class="linkLogoText">
                        Village Houses 
                    </span>              
                </a>
                
                <img id = "${lowerHouseType}Image" src="Images/${lowerHouseType}Icon.png" alt="House Icon" 
                     title="House Icon">
                          
                <!-- Creates the menu button (If the website is used on mobile) -->
                <input class="menu-btn" type="checkbox" id="menu-btn" />
                <label class="menu-icon" for="menu-btn">
                    <span class="navicon"></span>
                </label>
                
                <!-- Creates the menu (In which it holds all the buttons on the nav bar) -->
                <ul class="menu">
                    <li>
                        <a href="Help" class="linkHeaderButton"> Help </a>
                    </li>
                    <li>
                        <a href="About" class="linkHeaderButton"> About</a>
                    </li>
                    <li>
                        <form class = "headerButtons" action = "HouseAnnouncements" method = "Post"> 
                            <input
                                type="submit"
                                value="Announcements"
                                class = "headerText houseHeaderText"
                            >
                        </form>
                    </li>
                    <li>             
                        <form class="headerButtons" action = "HousePolls" method = "Post"> 
                            <input
                                type="submit"
                                value="Polls"
                                class = "headerText houseHeaderText"
                            >
                        </form>
                    </li>
                    <li>
                        <!-- Creates dropdown button -->
                        <div class="dropdown headerButtons ">
                          <button class="headerText">Account&nbsp;&#8964;</button>
                          <div class="dropdown-content">
                            <a href="FeedbackPopUp"> Feedback</a>
                            <a href="SettingsPopUp"> Settings
                                <!-- Displays Image-->
                                <img id = "settingsIconImage" src="Images/settingsIcon.png" alt="Settings Icon" 
                                    title="Settings Icon"> 
                            </a>
                            <form action = "index" class = "dropdown-form" method = "Post"> 
                                <input
                                    type="submit"
                                    value="Sign Out"
                                    class = "dropdown-form-text"
                                >
                            </form>
                          </div>
                        </div>
                    </li>
                </ul>
        </div>                    
                
        <!-- Creates the verify remove feedback pop up -->             
        <div id="verifyPopUp" class="overlay${verifyDisplay}">
            <div class="popUp2">
                <!-- Creates title within the block-->
                <span class = "announcementHeaders popUpTextTitle"> Remove Feedback</span> <a class="close announcementHeaders" href="Feedback"> X </a> </br> </br>
                </br>
                <!-- Creates feedback info form -->
                <form id = "announcementInformation" action="RemoveFeedback" method="Post"> 
                    
                    Are you sure you would like to remove the feedback?
                    
                    </br>
                    </br>
                    
                    <!-- Creates form that removes the given feedback --> 
                    <input class="addButton hazardButton" type="submit" name="removeFeedback" value="Yes">
                </form>

                </br>
                
            </div>
        </div>

        <!-- Creates the settings pop up -->  
        <div id="settingsPopUp" class="overlay${settingsDisplay}">
            <div class="popUp">
                <!-- Creates title within the block-->
                <span class = "announcementHeaders popUpTextTitle"> Settings </span> <a class="close announcementHeaders" href="HouseAnnouncements"> X </a> </br> </br>
                </br>
                
                <!-- Creates settings info form -->
                <form id = "settingsInformation" action="SaveSettings" method="Post"> 
                    
                    Current Notification Setting: </br> ${notificationSetting} </br> </br>
                    
                    Notifications: 
                    <select id="notificationTypes" name="notificationTypes">
                      <option value="allowAll">All</option>
                      <option value="onlyAnnouncements">Only Announcements</option>
                      <option value="onlyPolls">Only Polls</option>
                      <option value="allowNone">None</option>
                    </select>
                    
                    </br> 
                    </br>
                    
                    <!-- Displays success/error message -->
                    <div class="${houseMessageDisplay}">
                        <span class="${houseMessageType}Message">${houseMessageResult}</span>
                    </div>
                    
                    <br class="${houseMessageDisplay}">
                    
                    <!-- Creates button that saves the user's settings --> 
                    <input class="addButton saveSettingsButton" type="submit" name="saveSettings" value="Save Settings">
                </form>

                </br>
                
            </div>
        </div>   
                    
        </br>
        </br>
        </br>
        </br>
        </br>
                    
        <!-- Creates the title of the feedback page -->
        <h1 id = "title">
            Here is the Feedback:  
        </h1>
        
        <!-- Displays Users' feedback -->
        <c:forEach var="row" items="${feedbackData}">
            <div class = "informationBlocks">
                <h1 class = "informationTitle feedbackEmailFormat"> <c:out value="${row.get(2)}"/> </h1> 
                <form class="close ${authorization} closeInformation feedbackCloseFormat" action="RemoveFeedbackPopUp" method="Post">  
                    <input type="submit" name="removeFeedback${row.get(1)}" value="X"> 
                </form>

                </br>
                </br>
                </br>
                <div class = "informationText feedbackText"> 
                    <c:out value="${row.get(3)}"/>
                    </br>
                    </br>
                </div>
            </div>
            </br>
        </c:forEach>
              
        </br>
        </br>
        </br>
        </br>
    </body>
</html>
