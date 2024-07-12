Feature: Homepage

 Scenario: User navigates the homepage and verifies the navigation menu items are present
  Given User navigates to home page
  And User sees "About" navigation menu item
  And User sees "Services" navigation menu item
  And User sees "Customers" navigation menu item
  And User sees "Nearsurance" navigation menu item
  And User sees "Resources" navigation menu item
  And User sees "Careers" navigation menu item
  Then User scrolls down the home page to see "Strypes Ltd. All Rights Reserved." disclaimer

 Scenario: User navigates to homepage and verifies the slide content
  Given User navigates to home page
  And User verifies the slider "1 / 3" has "Building Smart Solutions" headings value
  And User verifies the slider "1 / 3" has "We provide end-to-end software solutions" description
  And User verifies the slider "2 / 3" has "Nearsurance" headings value
  And User verifies the slider "2 / 3" has "Our unique outsourcing model that brings business success" description
  And User verifies the slider "3 / 3" has "Start your career in IT" headings value
  And User verifies the slider "3 / 3" has "We are here to help you kick start and advance your career." description
  Then User verifies that "Learn more" slider button is present

 Scenario: User verifies the submenus of the navigation menu
  Given User navigates to home page
  And User hovers "About" navigation menu item
  And User sees the "Our brands" sub-menu option in the navigation menu
  And User sees the "Our promises" sub-menu option in the navigation menu
  And User sees the "Our leadership" sub-menu option in the navigation menu
  And User hovers "Services" navigation menu item
  And User sees the "DevOps" sub-menu option in the navigation menu
  And User sees the "Digital transformation" sub-menu option in the navigation menu
  And User sees the "Mobility and Transportation" sub-menu option in the navigation menu
  And User sees the "Remote Diagnostics, Monitoring and Predictive Maintenance" sub-menu option in the navigation menu
  And User sees the "SDaaS" sub-menu option in the navigation menu
  And User sees the "Software Integration and FLaaS" sub-menu option in the navigation menu
  And User sees the "Smart applications:" sub-menu option in the navigation menu
  And User sees the "Modularity Services" sub-menu option in the navigation menu
  And User hovers "Resources" navigation menu item
  And User sees the "Blog" sub-menu option in the navigation menu
  And User sees the "Whitepapers" sub-menu option in the navigation menu
  Then User sees the "Success Stories" sub-menu option in the navigation menu

 Scenario Outline: User navigates and verifies <filter name> Careers filter
  Given User navigates to home page
  And User clicks "Careers" navigation menu item
  And User verifies landing on "https://strypes.eu/careers/" page
  And User sees "We are hiring" text
  And User sees "Search open positions" text
  Then User applies "<filter name>" filter in open positions and prints them

   Examples:
   |filter name        |
   |Python Development |
   |C/C++ Development  |
   |DevOps Engineering |
   |Quality Assurance  |
   |Other              |

 Scenario Outline: User verifies the Connect <social media> link is working
  Given User navigates to home page
  And User scrolls down the home page to see "Strypes Ltd. All Rights Reserved." disclaimer
  And User finds "<social media>" social media link and verifies the image is loaded
  And User clicks on "<social media>" social media link
  And User switches to 2 browser tab
  Then User verifies landing on "<link>" page

  Examples:
  |social media|link                                                                                                                                                 |
  |Facebook    |https://www.facebook.com/StrypesBulgaria/                                                                                                            |
  |Instagram   |https://www.instagram.com/strypes.group/                                                                                                             |
  |Youtube     |https://consent.youtube.com/m?continue=https%3A%2F%2Fwww.youtube.com%2Fchannel%2FUC_bZ_Mu0G0OdQhcpsHq7w-w%3Fcbrd%3D1&gl=BG&m=0&pc=yt&cm=2&hl=en&src=1|
  |Linkedin    |https://www.linkedin.com/company/strypes/                                                                                                            |
  |Twitter     |https://x.com/strypesICT                                                                                                                             |
