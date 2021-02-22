# Your Electric Bills
> This system is used to store your electric bills

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info
Our project is a storage tpye application and data organizer of your electric bills.

## Screenshots
![Example screenshot](./img/screenshot.png)


## Setup
You're a developer and I am new to Github. :)

## Code Examples
Show examples of usage:
`public Cursor getMeterBoardsCursor(String search){
        String sql = "SELECT * FROM TblMeterBoard WHERE callName LIKE '%" +search+ "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }`
    This is used to view all the meterboards from the database.

## Features
List of features ready and TODOs for future development
* Views the data by bill, meter, electricity used, and etc.
* Shows the gap of the bills per month of the specified meter using graph.
* Shows the gap of kilowatts of the meters using graph.

To-do list:
* Create more graph specifically for bill fee per month.
* Create more accurate data for dates.
* Design better.

## Status
Project is: _in progress_ because there is big room for improvement. :)

## Inspiration
Credits to Myself and Kenneth Villamido. Project inspired by unexpected electric cutoff. Based on a true story of Gark Godwin Duque. :)

## Contact
Created by Gark Godwin Duque and Kenneth Villamido.
Contact us through - +63 936 210 1653
:joy: :joy: :joy:
