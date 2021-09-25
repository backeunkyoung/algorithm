import datetime

rfile = open("calendar.inp", "r")
wfile = open("calendar.out", "w")

def isLeapYear(year) : 
    year = int(year)
    return (year % 4 == 0 and year % 100 != 0) or year % 400 == 0

def changeToDate(inputDate) :
    (year, month, day) = inputDate.split("-")
    date = datetime.datetime(int(year), int(month), int(day))
    # print(date)
    return date

def diffDays(startDate, endDate) : return abs(startDate - endDate).days

def lastDay(year, month) :
    lastDay = 0

    if month == str(2) :
        if isLeapYear(year) : lastDay = 29
        else : lastDay = 28
    else :
        month = int(month)
        if month == 1 or month == 3 or month == 5 or month == 7 or month == 8 or month == 10 or month == 12 : lastDay = 31
        else : lastDay = 30

    return lastDay

def dayOfWeek(dayOfWeek) :
    if dayOfWeek == "Mon" : return 0
    elif dayOfWeek == "Tue" : return 1
    elif dayOfWeek == "Wed" : return 2
    elif dayOfWeek == "Thu" : return 3
    elif dayOfWeek == "Fri" : return 4
    elif dayOfWeek == "Sat" : return 5
    elif dayOfWeek == "Sun" : return 6
    else : return 99

def getdayList(lastDay, year, month, day) :
    dateList = []
    for i in range(1, int(lastDay)+1) :
        dayOfWeek = datetime.date(int(year), int(month), i).weekday()
        if int(day) == int(dayOfWeek) :
            dateList.append(i)
    return dateList
    
def typeChange(year, month, count, dayOfWeek) :
    # print(year, " ", month, " ", count, " ", dayOfWeek)
    day = 0

    if month == str(2) :
        isLeap = isLeapYear(year)

        if not isLeap and int(count) == 5 : 
            day = 99
        elif isLeap and int(count) == 5 :
            startDayOfWeek = datetime.date(int(year), int(month), 1).weekday()
            # print(startDayOfWeek, " ", dayOfWeek)
            if (startDayOfWeek != dayOfWeek) :
                day = 99
            else :
                dayList = getdayList(29, year, month, dayOfWeek)
                # print(dayList)
                day = dayList[int(count)-1]
        else :
            dayList = getdayList(28, year, month, dayOfWeek)
            # print(dayList)
            day = dayList[int(count)-1]

    else :
        monthOfLastDay = lastDay(year, month)
        startDayOfWeek = datetime.date(int(year), int(month), 1).weekday()
        lastDayOfWeek = datetime.date(int(year), int(month), int(monthOfLastDay)).weekday()

        if count == str(5) :
            max = int(startDayOfWeek)
            min = int(lastDayOfWeek)

            if max > min :
                if dayOfWeek < min and dayOfWeek > max :
                    day = 99
                else :
                    dayList = getdayList(monthOfLastDay, year, month, dayOfWeek)
                    # print(dayList)
                    day = dayList[int(count)-1]
            else :
                dayList = getdayList(monthOfLastDay, year, month, dayOfWeek)
                # print(dayList)
                day = dayList[int(count)-1]
        else :
            dayList = getdayList(monthOfLastDay, year, month, dayOfWeek)
            # print(dayList)
            day = dayList[int(count)-1]

    # print("day : ", day)
    newDate = datetime.datetime(int(year), int(month), int(day))

    return newDate
        
while True :
    line = str(rfile.readline()).strip()

    if line == str(-1) : break

    (inputType, inputStartDate, inputEndDate) = line.split(" ")

    try :
        if inputType == str(0) :
            startDate = changeToDate(inputStartDate)
            endDate = changeToDate(inputEndDate)

            # print("startDate : ", startDate)
            # print("endDate : ", endDate)

            result = diffDays(startDate, endDate)
            # print(result)
            wfile.write("%s\n" % result)

        if inputType == str(1) :
            startDate = changeToDate(inputStartDate)

            (endYear, endMonth, endCount, endDayOfWeek) = inputEndDate.split("-")
            endDayOfWeek = dayOfWeek(endDayOfWeek)
            newEndDate = typeChange(endYear, endMonth, endCount, endDayOfWeek)

            # print("startDate : ", startDate)
            # print("newEndDate : ", newEndDate)

            result = diffDays(startDate, newEndDate)
            # print(result)
            wfile.write("%s\n" % result)

        if inputType == str(2) :
            (startYear, startMonth, startCount, startDayOfWeek) = inputStartDate.split("-")
            startDayOfWeek = dayOfWeek(startDayOfWeek)
            newStartDate = typeChange(startYear, startMonth, startCount, startDayOfWeek)

            endDate = changeToDate(inputEndDate)

            # print("newStartDate : ", newStartDate)
            # print("endDate : ", endDate)

            result = diffDays(newStartDate, endDate)
            # print(result)
            wfile.write("%s\n" % result)

        if inputType == str(3) :
            (startYear, startMonth, startCount, startDayOfWeek) = inputStartDate.split("-")
            (endYear, endMonth, endCount, endDayOfWeek) = inputEndDate.split("-")

            startDayOfWeek = dayOfWeek(startDayOfWeek)
            endDayOfWeek = dayOfWeek(endDayOfWeek)

            newStartDate = typeChange(startYear, startMonth, startCount, startDayOfWeek)
            # print("newStartDate : ", newStartDate)

            newEndDate = typeChange(endYear, endMonth, endCount, endDayOfWeek)
            # print("newEndDate : ", newEndDate)

            result = diffDays(newStartDate, newEndDate)
            # print(result)
            wfile.write("%s\n" % result)
    except :
        result = -1
        # print(-1)
        wfile.write("%s\n" % result)

rfile.close()
wfile.close()