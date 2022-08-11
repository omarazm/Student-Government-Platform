<!-- 
Omar Abouelazm
Period: 3
-->
<!--This is the about page of the website. It allows for 
the user to obtain information on what the website 
is used for and on their respective houses.-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
        <link href="CSS/VillageHousesCss.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About</title>
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
                
        </br>
        </br>
        </br>
        </br>
        </br>
        
        <!-- Creates the title of the about page -->
        <h1 id = "title">
            About
        </h1>

        <!-- Creates slideshow -->
        <div id = "aboutSlidesBlock" class="lightFormat">
            <div id="sliderBox">
                <img class = "slideImage" src="Images/groupPumpkinPhoto.JPG" alt="Slide Show" title="Slide Show"> 
                <img class = "slideImage" src="Images/happyPumpkinPhoto.JPG" alt="Slide Show" title="Slide Show"> 
                <img class = "slideImage" src="Images/stuff.JPG" alt="Slide Show" title="Slide Show"> 
                <img class = "slideImage" src="Images/funnyLeadershipPhoto.JPG" alt="Slide Show" title="Slide Show"> 
                <img class = "slideImage" src="Images/groupPumpkinPhoto.JPG" alt="Slide Show" title="Slide Show"> 
            </div>
        </div>
            
        </br>
        </br>
  
        <!-- Creates the about website section -->
        <div id = "aboutWebsiteBlock" class="homeFormat">
            
            <!-- Displays Image-->
            <img id = "informationLogoImage" src="Images/informationLogo.png" alt="Information Logo" 
                title="Information Logo">

            </br>
                
            <!-- Creates title within the block-->
            <span class = "aboutHeaders" id = "aboutWebsite"> About the Website </span> 
            
            </br> 
            
            <div class="red-border">  
            </div>
            
            </br>

            Welcome to the Village Houses Website! This website allows for you to view announcements, 
            polls, and other information about your house. Including this, you can send feedback
            to us, so that we can implement and integrate your ideas and concerns into the house
            system. 

            </br>
            </br>
        </div>
            
        <!-- Creates the leadership section -->
        <div id = "leadershipBlock">
            </br>
            <!-- Creates title within the block-->
            <span class = "aboutHeaders" id = "leadershipTeam"> Leadership Team </span> 
            </br> 
            
            <div class="red-border">  
            </div>
            
            </br>

            <!-- Creates a table -->
            <table id="leadershipTable">
                    <!-- Creates table header -->
                    <tr>
                        <th> Member Name </th>
                        <th> Grade </th>
                        <th> Role </th>
                    </tr>
                    <!-- Goes through each row of data and prints out 
                        the data on the webpage-->
                    <c:forEach var="row" items="${leadershipData}">
                        <tr>
                            <td><c:out value="${row.get(0)}"/></td>
                            <td><c:out value="${row.get(2)}"/></td>
                            <td><c:out value="${row.get(1)}"/></td>
                        </tr>
                    </c:forEach>
            </table>
            </br>
        </div>
        
        <br>
            
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
    </body>
</html>
