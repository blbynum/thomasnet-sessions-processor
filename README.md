# ThomasNet Sessions Proccessor Application

##I wrote this years ago and it is terrible. No OOP concepts, no use of awesome tools. Not really sure what I was thinking. I've decided not to go back and fix it, since I no longer have access to the data it proccesses or any use for it, but I'm going to keep it here to look back and laugh about.

The purpose of this program is to take massive (and useless) amounts of analytics data downloaded in the form of CSV files from 
Thomasnet and to process that data into an easily readable set of leads for the sales team. 

The application will produce two CSV filesfor each CSV file of ThomasNet data provided. One file will be regional sales leads, 
the other national sales leads. If "Build Regional Address List" is selected, the application will build a CSV file with the addresses
of each regional sales lead by running a Google search for each lead (provided an address was found). This CSV file is designed to be
imported into a Microsft Word Mailing Label Template.

The application also includes a blacklist and a whitelist. Add items to the blacklist (through the options in the dropdown menu bar) to
leave any matching leads out of the produced list. Add items to the whitelist (also in the dropdown menu bar) to add a custom priority
increase to matching leads.

When you open the program you will see this Window

![](https://i.imgur.com/sQB15dk.jpg)



If you wish, you can check the "Custom File Locations" Box

![](https://i.imgur.com/225nT4o.jpg)


The Application also works with a Blacklist and a Whitelist to refine your results. Access the settings for these lists through the dropdown menus at the top

![](https://i.imgur.com/vfVLgbZ.jpg)



When you click on the option to add an item to the blacklist, a popup window will prompt you for input. You can type the term that is to be blacklisted and select which field it will appear in.

![](https://i.imgur.com/6yx6YFS.jpg)



The same can be done with the Whitelist. With the Whitelist, however, you must also select the priority of this item. This will affect how much the defined term will be bumped up in the list of leads.

![](https://i.imgur.com/EEh6wyq.jpg)



Here you see the original logs.csv file downloaded from Thomasnet. For each entry, it shows IP Address, Company Name (ISP), Domain Name, Location (City/State/Country), ZIP Codde, Industry, Subindustry, B2B/B2C, Business Type, Referer, Search Terms, Time and Date of Visit, Time On Site, and Pages Clicked. It has over 8,000 entries, far too many for any human to sort through. You will also notice that the refer column is mostly made up of very long URLs.

![](https://i.imgur.com/HnTraPH.jpg)



Select the logs.csv file and click "Proccess and Save". When you see the message pop up at the bottom informing you that the operation was successful, then you know the new CSV files have been created in the directory with logs.csv (unless you chose to use custom file locations).

![](https://i.imgur.com/U9CPSrV.jpg)



You should now also have a regional list, a national list, and (if you checked "Build Regional Address List" before processing) a Regional Address List. The regional and national lists will be the same, except that the regional list will contain regional leads, and the national list will contain the rest. Here in the regional list from this log, notice that the results have been cut down to 93 from well over 8,000. You will also notice the addition of a Priority column on the right. The list, by default, will be sorted by this column in decending order. You will also see that the referes have been cut from long URLs to just the names of the refering sites. 

![](https://i.imgur.com/RIsy8za.jpg)



Here you see the Regional Address List. It is formatted to be imported into a Microsoft Word Mailing Label Template. As you can see, the application was able to find the company names and and mailing addresses of 53 of the 93 companies. This would have taken hours for an employee, or just about minute for the application. 

![](https://i.imgur.com/xHoSorF.jpg)

This application was developed by Ben Bynum.
