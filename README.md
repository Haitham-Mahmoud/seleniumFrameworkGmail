# seleniumFrameworkGmail
Selenium framework for java, maven, testng and POM

Using DDT by JOSN files

# Pages
 - Base Page to customize methods to be easy in usage 
 - Every page = Create class
 - Every class contains Page Factory for Web Elements + Methods for actions

# Utilities
  - Constant for fixed data
  - Helper for screenshot in case the test case failed

# Datadriven
  - jsonDataReader to read data from JSON file
  - JSON data file

# Tests
  - Base Test to define testng annotations @Before / @After
  - Test cases to define testng annotations @Test
  
# Git Commands - CMD 
 - From project loaction > git version [to know the version] 
 - From project loaction> git init [to create initiate git folder hidden]
 - From project loaction> git status
 - From project loaction> git add with folder [Write the first letters and press tab after that keep slash / after last letter] for example: [Git add src/]
 - From project loaction> git add with file [Write the first letters and press tab] for example: [Git add pom.xml]
 - From project loaction> git commit -m "Your comment"
 - From project loaction> git remote add origin https://github.com/Username/RepositoryName
 - From project loaction> git push -u origin master [to push your code remotly with first time]
 - From project loaction> git push -f origin master [to push your code remotly with updates]
 - From project loaction> git pull [to get updates local by others]
