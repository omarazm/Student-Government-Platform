<%-- 
    Document   : AdminPage
    Created on : May 5, 2020, 12:31:34 PM
    Author     : oabouelazm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
        <link href="CSS/VillageHousesCss.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <!-- Creates the header/navigation bar-->
        <div id = "houseHeader">   
            
                <!-- Creates the brand link/logo-->             
                <a href="#" class="headerButtons linkHeaderButton linkLogo" > 
                    <img id = "villageIconImage" src="Images/TheVillageSchoolLogo.png" alt="Village Icon" 
                        title="Village Logo"> 
                    <span class="linkLogoText">
                        Village Houses 
                    </span>              
                </a>
                            
                <!-- Creates the menu button (If the website is used on mobile) -->
                <input class="menu-btn" type="checkbox" id="menu-btn" />
                <label class="menu-icon" for="menu-btn">
                    <span class="navicon"></span>
                </label>
                
                <!-- Creates the menu (In which it holds all the buttons on the nav bar) -->
                <ul class="menu">
                    <li>
                        <!-- Creates dropdown button -->
                        <div class="dropdown headerButtons ">
                          <button class="headerText">Account&nbsp;&#8964;</button>
                          <div class="dropdown-content">
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
            Profile Information
        </h1>
            
        <!-- Creates the leadership section -->
        <div id = "leadershipBlock" class = "homeFormat">
            </br>
            <!-- Creates title within the block-->
            <span class = "aboutHeaders" id = "leadershipTeam"> House Members </span> 
            </br> 
            
            <div class="red-border">  
            </div>
            
            </br>

            <!-- Creates a table -->
            <table id="leadershipTable">
                    <!-- Creates table header -->
                    <tr>
                        <th> MemberEmail </th>
                        <th> MemberName</th>
                        <th> HouseType </th>
                    </tr>
                    <!-- Goes through each row of data and prints out 
                        the data on the webpage-->
                    <c:forEach var="row" items="${houseMembersData}">
                        <tr>
                            <td><c:out value="${row.get(0)}"/></td>
                            <td><c:out value="${row.get(1)}"/></td>
                            <td><c:out value="${row.get(2)}"/></td>
                        </tr>
                    </c:forEach>
            </table>
        </div>
                        
        </br>    
        
        <!-- Creates profile info form -->
        <form id = "profileInformation" class = "adminInformation" action="AddMember" method="Post">  
            </br>
            
            <!-- Creates title within the block-->
            <span class = "aboutHeaders"> Add Member </span> 
            
            </br>
            
            <div class="red-border">  
            </div>
            
            </br>
            
            <div id = "userNameField">
                Email: </br>                     
                <input class="fieldFormat" id="memberEmail" type="text" name="memberEmail">
            </div> 

            </br>

            <div id = "passwordField">      
                Member Name: </br>
                <input class="fieldFormat" id="memberName" type="text" name="memberName">
            </div>
            
            </br>
            
            <div id = "passwordField">      
                House Type: </br>
                <input class="fieldFormat" id="houseType" type="text" name="houseType">
            </div>
            
            </br>
            </br>
            
            <div class="${addMemberMessageDisplay}">
                <span class="${addMemberMessageType}Message">${addMemberMessageResult}</span>
            </div>
            <br class="${addMemberMessageDisplay}">
            
            </br>
            
            <!-- Creates button that logs in existing user --> 
            <input class="logInFormat blueButton" type="submit" name="AddMember" value="Add">
                            
            </br>
            </br>
            </br>
        </form>
        
        <!-- Creates profile info form -->
        <form id = "profileInformation" class = "adminInformation lightFormat" action="DeleteMember" method="Post">    
            </br>
            </br>
            
            <!-- Creates title within the block-->
            <span class = "aboutHeaders"> Delete Member </span> 
            
            </br>
            
            <div class="red-border">  
            </div>
            
            </br>
            </br>
            
            <div id = "userNameField">
                Email: </br>                     
                <input class="fieldFormat" id="deleteEmail" type="text" name="deleteEmail">
            </div> 
            
            </br>
                    <div class="${deleteMemberMessageDisplay}">
                        <span class="${deleteMemberMessageType}Message">${deleteMemberMessageResult}</span>
                    </div>
            <br class="${deleteMemberMessageDisplay}">
            
            </br>
            
            <!-- Creates button that logs in existing user --> 
            <input class="logInFormat redButton adminButtons" type="submit" name="DeleteMember" value="Delete">
                    
            </br>
            </br>
            </br>
        </form>
        
        <!-- Creates the leadership section -->
        <div id = "leadershipBlock" class = "homeFormat ${houseLeadersDisplay}">
            </br>
            <!-- Creates title within the block-->
            <span class = "aboutHeaders" id = "leadershipTeam"> House Leaders </span> 
            </br> 
            
            <div class="red-border">  
            </div>
            
            </br>

            <!-- Creates a table -->
            <table id="leadershipTable">
                    <!-- Creates table header -->
                    <tr>
                        <th> LeaderEmail </th>
                        <th> LeaderName</th>
                        <th> HousePosition </th>
                        <th> GradeLevel </th>
                    </tr>
                    <!-- Goes through each row of data and prints out 
                        the data on the webpage-->
                    <c:forEach var="row" items="${houseLeadersData}">
                        <tr>
                            <td><c:out value="${row.get(0)}"/></td>
                            <td><c:out value="${row.get(1)}"/></td>
                            <td><c:out value="${row.get(2)}"/></td>
                            <td><c:out value="${row.get(3)}"/></td>
                        </tr>
                    </c:forEach>
            </table>
        </div>
            
        <!-- Creates profile info form -->
        <form id = "profileInformation" class = "adminInformation" action="AddLeader" method="Post">  
            </br>
            
            <!-- Creates title within the block-->
            <span class = "aboutHeaders"> Add Leader </span> 
            
            </br>
            
            <div class="red-border">  
            </div>
            
            </br>
            
            <div id = "userNameField">
                Email: </br>                     
                <input class="fieldFormat" id="leaderEmail" type="text" name="leaderEmail">
            </div> 

            </br>

            <div id = "passwordField">      
                Member Name: </br>
                <input class="fieldFormat" id="leaderName" type="text" name="leaderName">
            </div>
            
            </br>
            
            <div id = "passwordField">      
                House Position: </br>
                <input class="fieldFormat" id="housePosition" type="text" name="housePosition">
            </div>
            
            </br>
            
            <div id = "passwordField">      
                Grade Level: </br>
                <input class="fieldFormat" id="gradeLevel" type="number" name="gradeLevel">
            </div>
            
            </br>
            </br>
            
            <div class="${addLeaderMessageDisplay}">
                <span class="${addLeaderMessageType}Message">${addLeaderMessageResult}</span>
            </div>
            <br class="${addLeaderMessageDisplay}">
            
            </br>
            
            <!-- Creates button that logs in existing user --> 
            <input class="logInFormat blueButton" type="submit" name="AddLeader" value="Add">
                            
            </br>
            </br>
            </br>
        </form>
        
        <!-- Creates profile info form -->
        <form id = "profileInformation" class = "adminInformation lightFormat" action="DeleteLeader" method="Post">    
            </br>
            </br>
            
            <!-- Creates title within the block-->
            <span class = "aboutHeaders"> Delete Leader </span> 
            
            </br>
            
            <div class="red-border">  
            </div>
            
            </br>
            </br>
            
            <div id = "userNameField">
                Email: </br>                     
                <input class="fieldFormat" id="deleteEmail" type="text" name="deleteLeaderEmail">
            </div> 
            
            </br>
                    <div class="${deleteLeaderMessageDisplay}">
                        <span class="${deleteLeaderMessageType}Message">${deleteLeaderMessageResult}</span>
                    </div>
            <br class="${deleteLeaderMessageDisplay}">
            
            </br>
            
            <!-- Creates button that logs in existing user --> 
            <input class="logInFormat redButton adminButtons" type="submit" name="DeleteLeader" value="Delete">
                    
            </br>
            </br>
            </br>
        </form>
        
    </body>
</html>
