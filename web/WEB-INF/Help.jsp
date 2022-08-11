<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
        <link href="CSS/VillageHousesCss.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help</title>
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
                                             
                <input class="menu-btn" type="checkbox" id="menu-btn" />
                <label class="menu-icon" for="menu-btn">
                    <span class="navicon"></span>
                </label>
                
                <ul class="menu">
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
                    <li class="${authorization}">
                        <a href="Feedback" class="linkHeaderButton"> View Feedback </a>   
                    </li>
                    <li>
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
            
        </br>
        </br>
        </br>
        </br>
        </br>
        
        <!-- Creates the title of the input page -->
        <h1 id = "title">
            Help
        </h1>
        
        <!-- Creates the contact us section -->
        <div id = "contactHelpBlock" class="lightFormat">
            </br>
            <!-- Creates title within the block-->
            <span class = "aboutHeaders" id = "contactHelp"> Contact Us </span> 
            </br> 
            
            <div class="red-border">  
            </div>
            
            </br>

            If you have any questions, please feel free to contact us at: 
            </br>
            </br>
            <a href="mailto:omar_abouelazm@s.thevillageschool.com">omar_abouelazm@s.thevillageschool.com</a> <!-- or at <a href="mailto:natalie.goodwin@thevillageschool.com">natalie.goodwin@thevillageschool.com</a> -->
            
            </br>
            </br> 
        </div>
              
        </br>
        </br>
        
        <!-- Creates clear data button -->
        
        <!--
        <form id="clearDataBlock" action="clearData" method="Post">   
            <input id="clearDataButton" type="submit" name="clearData" value="Clear Data">
        </form>
        -->
    </body>
</html>
