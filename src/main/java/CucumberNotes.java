
public class CucumberNotes {
	//1) What it Gherkin
		//It is a Business Readable, Domain Specific Language that lets you describe software's behavior.
		//Example: Pop up message is displayed when buttons are clicked and errors are gone
		//Keywords Used in Cucumber - Scenario, Feature, Feature files, Scenario outlines, Step Definition

	//Scenarios:
	//In Cucumber testCases are represented as Scenarios.
	//Scenarios contain steps which are equivalent to test steps and use the following keywords(Gherkin syntax) to denote them: Given, When, Then, But, and And(case sensitive)
		//Given - Preconditions are mentioned in given keyword
		//When - The purpose of the When steps is to describe the user action
		//Then - The purpose of Then steps is to observe the expected output. The observation should be related to the business alue/benefits of your feature description
	
	//Scenario: Make minimum Due payment
		//Given - User is on Pay credit card Page
		//When -  user fills all details and selects minimum amount option
		//And - User clicks an Pay button
		//Then - Credit Card confirmation page is displayed
	//And if reference number is displayed -> And is for Positive testing 
	//But error message is not displayed -> But is for Negative testing
	
	
	//Feature and Feature File
		//Feature is a business requirement.
		//Feature file acts as a test suite which consist of all scenarios.
	
		//In Cucumber. feature files contain scenarios.We can simply create feature file with .feature extension.Scenario belonging to specific area of Application will be grouped into one Feature file
		//The Text that immediately follows the Feature keyword, and is in the same line, is the Title of the Feature File.
		//Feature file should contain either the Scenario or Scenario Outline. hThe naming conventions for Feature files should be lowercase with .feature extension.
	
	
		//Feature: Credit card Payment
		//Scenario: Make minimum due payment
		//Given - User is on Pay credit card Page
		//When -  user fills all details and selects minimum amount option
		//And - User clicks an Pay button
		//Then - Credit Card confirmation page is displayed

}
