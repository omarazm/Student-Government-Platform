<!-- 
Omar Abouelazm
Period: 3
-->
<!--This is the house polls page of the website. It allows for 
a user to see their respective house polls. Admin can add 
polls through this page-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <link href= "https://fonts.googleapis.com/css2?family=Dosis:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
        <link href="CSS/VillageHousesCss.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Polls</title>
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
                    <li class="${authorization}">
                        <a href="addPollPopUp" class="linkHeaderButton"> Add Poll</a>
                    </li>
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

        <!-- Creates add poll pop up -->
        <div id="addPollPopUp" class="overlay${addPollDisplay}">
            <div class="popUp5">
                <!-- Creates title within the block-->
                <span class = "announcementHeaders popUpTextTitle"> Add Poll </span> <a class="close announcementHeaders" href="HousePolls"> X </a> </br> </br>
                </br>
                <!-- Creates profile info form -->
                <form id = "pollInformation" action="AddPoll" method="Post"> 
                    <div>
                        Question: 
                        <input id="questionField" onfocus="this.value = ''" type="text" name="questionField">
                    </div> 

                    </br>

                    <div>      
                        Answer Choice 1: 
                        <input id="answer1Field" onfocus="this.value = ''" type="text" name="answer1Field">
                    </div>
                    
                    </br>

                    <div>      
                        Answer Choice 2: 
                        <input id="answer2Field" onfocus="this.value = ''" type="text" name="answer2Field">
                    </div>
                    
                    </br>
                    
                    <div>      
                        Answer Choice 3: 
                        <input id="answer3Field" onfocus="this.value = ''" type="text" name="answer3Field">
                    </div>
                    
                    </br>
                    
                    <div>      
                        Answer Choice 4: 
                        <input id="answer4Field" onfocus="this.value = ''" type="text" name="answer4Field">
                    </div>
                    
                    </br>
                    
                    <div>      
                        Answer Choice 5: 
                        <input id="answer5Field" onfocus="this.value = ''" type="text" name="answer5Field">
                    </div>

                    </br>
                    
                    <div class="${houseMessageDisplay}">
                        <span class="${houseMessageType}Message">${houseMessageResult}</span>
                    </div>
                    
                    <br class="${houseMessageDisplay}">
                    
                    <!-- Creates button that adds a poll --> 
                    <input class="addButton redButton" type="submit" name="AddPoll" value="Add">
                </form>

                </br>
                
            </div>
        </div>      
                
        <!-- Creates the verify remove poll pop up -->
        <div id="verifyPopUp" class="overlay${verifyDisplay}">
            <div class="popUp2">
                <!-- Creates title within the block-->
                <span class = "announcementHeaders popUpTextTitle"> Remove Poll </span> <a class="close announcementHeaders" href="HousePolls"> X </a> </br> </br>
                </br>
                <!-- Creates poll info form -->
                <form id = "announcementInformation" action="RemovePoll" method="Post"> 
                    
                    Are you sure you would like to remove the poll titled: "${pollQuestion}"
                    
                    </br>
                    </br>
                    
                    <!-- Creates form that removes poll --> 
                    <input class="addButton hazardButton" type="submit" name="removePoll" value="Yes">
                </form>

                </br>
                
            </div>
        </div>
                    
        </br>
        </br>
        </br>
        </br>
        </br>
                
        <!-- Creates the title of the polls page -->
        <h1 id = "title">
            Polls
        </h1>
            
        <!-- Displays polls -->
        <c:forEach var="row" items="${pollData}">
            <div class = "informationBlocks">
                <form class="close ${authorization} closeInformation" action="RemovePollPopUp" method="Post"> 
                    <input type="submit" name="removePoll${row.get(0)}" value="X"> 
                </form>
                <div class = "informationText"> 
                    <h1 class = "informationTitle"> <c:out value="${row.get(1)}"/> </h1> 
                    
                    <!--Displays options for voting if the user has not voted -->
                    <form class="${row.get(2)}" action="AddVote" method="Post">
                        <div class = "pollOptions">
                            <c:forEach items="${row}" var="answerItem" begin="5" end="9">
                                <input type="radio" id = "${answerItem.answer}${row.get(0)}" name="pollAnswer${row.get(0)}" value="${answerItem.answer}"> 
                                <label for="${answerItem.answer}${row.get(0)}">${answerItem.answer}</label><br>
                            </c:forEach>        
                        </div>
                        </br>
                        <input type="submit" name="votePoll${row.get(0)}" value="Vote"> 
                    </form>
                    
                    <!-- Displays the number of votes for each category if the user has already voted -->
                    <div class="${row.get(3)}">
                        <label> Your Vote: ${row.get(4)}</label><br><br>
                        <c:forEach items="${row}" var="answerItem" begin="5" end="9">
                            <label> ${answerItem.answer}: ${answerItem.voteNumber}</label><br>
                        </c:forEach> 
                        </br>
                        <form action="RemoveVote" method="Post">
                            <input type="submit" name="changeVotePoll${row.get(0)}" value="Change Vote"> 
                        </form>
                    </div>
                    
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

