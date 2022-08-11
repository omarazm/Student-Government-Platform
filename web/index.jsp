<!-- 
Omar Abouelazm
Period: 3
-->
<!--This is the home page of the website. It allows for 
the user to obtain a brief introduction on what the website 
is used for.-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
        <link href="CSS/VillageHousesCss.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
    </head>
    <body class = "body${bodyState}">
        <!-- Creates the header/navigation bar-->
        <div id = "houseHeader">
            
            <!-- Creates the brand link/logo-->             
            <a href="index" class="headerButtons linkHeaderButton linkLogo"> 
                <img id = "villageIconImage" src="Images/TheVillageSchoolLogo.png" alt="Village Icon" 
                    title="Village Logo"> 
                <span class="linkLogoText">
                    Village Houses 
                </span>              
            </a>
            
            <input class="menu-btn" type="checkbox" id="menu-btn" />
            <label class="menu-icon" for="menu-btn">
                <span class="navicon"></span>
            </label>
            
            <ul class="menu">
                <li class="linkButtonHolder">
                    <a href="logInPopUp" class="redButton linkButtonFormat"> Log In &raquo;</a>
                </li>
                <li class="linkButtonHolder">                       
                    <a href="signUpPopUp" class="blueButton linkButtonFormat"> Sign Up &raquo;</a>
                </li>
            </ul>
        </div>
        
        <!-- Creates log in pop up -->
        <div id="logInPopUp" class="overlay${logInDisplay}">
            <div class="popUp">
                <!-- Creates title within the block-->
                <span class = "logInHeaders popUpTextTitle"> Log In </span> <a class="close logInHeaders" href="index"> X </a> </br> </br>
                </br>
                <!-- Creates profile info form -->
                <form id = "profileInformation" action="LogIn" method="Post">                
                    <div id = "userNameField">
                        Email: </br>                     
                        <input class="fieldFormat" id="userName" type="text" name="userName">
                    </div> 

                    </br>

                    <div id = "passwordField">      
                        Password:  &nbsp; &nbsp;                    
                        <!-- Creates link that goes to the forgot password pop up -->
                        <a class = "forgotPasswordLink" href="forgotPasswordPopUp"> Forgot Password? </a> 
                        </br>
                        <input class="fieldFormat" id="password" type="password" name="password">
                    </div>

                    </br>
                    
                    <div class="${messageDisplay}">
                        <span class="errorMessage">${messageResult}</span>
                    </div>
                    
                    <br class="${messageDisplay}">
                    
                    <!-- Creates button that logs in existing user --> 
                    <input class="logInFormat redButton" type="submit" name="logIn" value="Log In">
                    
                </form>

                </br>
                
            </div>
        </div>
        
        <!-- Creates sign up pop up -->
        <div id="signUpPopUp" class="overlay${signUpDisplay}">
            <div class="popUp2">

                <!-- Creates title within the block-->
                <span class = "logInHeaders popUpTextTitle"> Sign Up </span> <a class="close logInHeaders" href="index"> X </a> </br> </br>
                </br>
                <!-- Creates sign up info form -->
                <form id = "signUpInformation" action="AddProfile" method="Post"> 
                    <div id="emailField">
                        Email: </br>
                        <input class="fieldFormat" id="email" type="text" name="email"> 
                    </div>

                    </br>

                    <div id="firstPasswordField">
                        Password:
                        
                        </br>
                        
                        <input class="fieldFormat" id="firstPassword" type="password" name="firstPassword">
                    </div>

                    </br>

                    <div id="secondPasswordField">
                        Re-Enter Password: </br>
                        <input class="fieldFormat" id="secondPassword" type="password" name="secondPassword">
                    </div>

                    </br>

                    <div class="${messageDisplay}">
                        <span class="${messageType}Message">${messageResult}</span>
                    </div>
                    
                    <br class="${messageDisplay}">
                    
                    <!-- Creates button that adds a new user -->
                    <input class="logInFormat" id ="signUpPosition" type="submit" name="signUp" value="Sign Up">
                </form>

                </br>

            </div>
        </div>
                
        <!-- Creates forgot password pop up -->                     
        <div id="forgotPasswordPopUp" class="overlay${forgotPasswordDisplay}">
            <div class="popUp3">
                
                <!-- Creates title within the block-->
                <span class = "logInHeaders"> <img id = "keyIconImage" src="Images/keyIcon.png" alt="Password Key Icon" 
                title="Password Key Icon"> <a class="close" href="index"> X </a> </span> </br> </br>
                </br>
                
                <form id="emailInformation" action="ForgotPassword" method="Post">     
                    <div id="emailField">
                        Email: 
                        <input class="fieldFormat" id="email" ntype="text" name="email">
                    </div>

                    </br>
                    
                    <div class="${messageDisplay}">
                        <span class="${messageType}Message">${messageResult}</span>
                    </div>
                    
                    <br class="${messageDisplay}">

                    <!-- Creates button that sends the password -->
                    <input class="logInFormat" type="submit" name="forgotPassword" value="Forgot Password">
                </form>

                </br>
                
            </div>
        </div>        
                    
                    
        <!-- Creates verify account pop up -->           
        <div id="verifyAccountPopUp" class="overlay${verifyAccountDisplay}">
            <div class="popUp4">
                
                <!-- Creates title within the block-->
                <span class = "logInHeaders"> <img id = "verificationIconImage" src="Images/verificationIcon.png" alt="Verification Icon" 
                title="Verification Icon"> <a class="close" href="index"> X </a> </span> </br> </br>
                </br>
                
                <form id="verifyAccountInformation" action="VerifyAccount" method="Post">                        
                    <div id="verificationCodeField">
                        Verification Code: </br>
                        <input class="fieldFormat" id="verificationCode" ntype="text" name="verificationCode">
                    </div>

                    </br>
                    
                    <div class="${messageDisplay}">
                        <span class="${messageType}Message">${messageResult}</span>
                    </div>
                    
                    <br class="${messageDisplay}">
                    
                    <!-- Creates form that verifies a user's account -->
                    <input class="logInFormat" type="submit" name="verifyAccount" value="Verify Account">
                </form>

                </br>
                
            </div>
        </div>    
        
         <!-- Creates the timed out pop up -->           
        <div id="timedOutPopUp" class="overlay${errorDisplay}">
            <div class="popUp">
                <!-- Creates title within the block-->
                <span class = "logInHeaders popUpTextTitle"> Error </span> <a class="close logInHeaders" href="index"> X </a> </br> </br>
                </br>
                    
                <span class="errorMessage errorFont"> Your Session Has Timed Out. <br> Please Log In Again </span>
                    
                </br>
                </br>
                
            </div>
        </div>
            
        </br>
        </br>
        </br>
        </br>
        </br>
                
        <!-- Creates the title of the home page -->
        <h1 id = "title">
            Welcome to the Village Houses.
        </h1>

        <!-- Creates slideshow -->
        <div id = "aboutSlidesBlock" class="homeFormat">
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
            <span class = "contactLinks">
                <a href="mailto:omar_abouelazm@s.thevillageschool.com">omar_abouelazm@s.thevillageschool.com</a> <!-- or at <a href="mailto:natalie.goodwin@thevillageschool.com">natalie.goodwin@thevillageschool.com</a> -->
            </span>
            </br>
            </br> 
            </br>
                        
            <a href="https://www.facebook.com/thevillageschooltx/" class="imageLink" target="_blank"> 
                <img id = "facebookImage" class="footerIcons" src="Images/facebookIcon.png" alt="Village Icon" 
                title="Village Logo"> 
            </a>
            <a href="https://www.youtube.com/channel/UC4xJeuym35Y8bwLMluh82SQ/videos"  class="imageLink" target="_blank"> 
                <img id = "youtubeImage" class="footerIcons" src="Images/youtubeIcon.png" alt="Village Icon" 
                title="Village Logo"> 
            </a>
            <a href="https://www.instagram.com/villagevikings/"  class="imageLink" target="_blank"> 
                <img id = "instagramImage" class="footerIcons" src="Images/instagramIcon.png" alt="Village Icon" 
                title="Village Logo"> 
            </a>
        </div>

        <!-- Creates the footer -->
        <!--
        <div class="footer"> -->
            <!-- Creates title within the block-->
            
            <!--
            <span class = "footerHeaders"> Embrace your community.  </span> 
                        
            </br>
            <div class="red-border">  
            </div>
            </br>
            
            <div class="centerBlock">
                <div class="rightFooterBlock">
                    <span class = "subFooterHeaders"> Follow Village </span>
                    </br>
                    </br>
                    <a href="https://www.facebook.com/thevillageschooltx/" class="imageLink" target="_blank"> 
                        <img id = "facebookImage" class="footerIcons" src="Images/facebookIcon_1.png" alt="Village Icon" 
                        title="Village Logo"> 
                    </a>
                    <a href="https://www.youtube.com/channel/UC4xJeuym35Y8bwLMluh82SQ/videos"  class="imageLink" target="_blank"> 
                        <img id = "youtubeImage" class="footerIcons" src="Images/youtubeIcon.png" alt="Village Icon" 
                        title="Village Logo"> 
                    </a>
                    <a href="https://www.instagram.com/villagevikings/"  class="imageLink" target="_blank"> 
                        <img id = "instagramImage" class="footerIcons" src="Images/instagramIcon_1.png" alt="Village Icon" 
                        title="Village Logo"> 
                    </a>
                </div>

                <div class="leftFooterBlock">
                    <span class = "subFooterHeaders"> Contact Us </span>
                    </br>
                    </br>
                    <span class = "contactLinks footerLinks">
                        <a href="mailto:omar_abouelazm@s.thevillageschool.com">omar_abouelazm@s.thevillageschool.com</a> </br>
                        <a href="mailto:natalie.goodwin@thevillageschool.com">natalie.goodwin@thevillageschool.com</a>
                    </span>
                </div>
            </div>
            -->
        <!--
        </div> -->
    </body> 
</html>