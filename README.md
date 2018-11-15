# INFO-6225

This repo is for handing on Assignments of 6225.

# part Apis for assignment1
<students>
1.GET
webapi/students/
webapi/students/{studentId}

2.POST
webapi/students/
body:
{
    "department": "IS",
    "firstName": "Yufei",
    "joiningDate": "2016-10-24 21:59:06",
    "lastName": "Gao",
    "registeredCourses": [
        "CloudComputing",
        "WebDesign"
    ],
    "studentId": "gao.yufei"
}
3.PUT
webapi/students/{studentId}
{
	"department":"InformationSystems"
}
4.DELETE
webapi/students/{studentId}

<courses>
1.GET
webapi/ courses /
webapi/ courses /{courseId}

2.POST
webapi/ courses /
body:
{
        "boardId": "WD",
        "courseId": "WebDesign",
        "department": "InformationSystems",
        "professorId": "a.jami",
        "roster": [
            "wang.yuchen3"
        ],
        "taId": "fu.jing1"
    }
3.PUT
webapi/ courses /{courseId}
{
	"department":"IS"
}
4.DELETE
webapi/ courses /{courseId}

<announcement>
1.GET
webapi/ announcements /
webapi/ announcements /{boardId}_{announcementId}

2.POST
webapi/ announcements
body:
{
        "announcementId": "CC2",
        "announcementText": "Hi!",
        "boardId": "CC"
    }

3.PUT
webapi/ announcements /{boardId}_{announcementId}
{
        "announcementText": "Hello!"
    }
4.DELETE
webapi/ announcements /{boardId}_{announcementId}

<board>
1.GET
webapi/boards/
webapi/boards/{boardId}

2.POST
webapi/boards/
body:
{
        "boardId": "WD",
        "courseId": "WebDsign"
    }
3.PUT
webapi/boards/{boardId}
{
        "courseId": "WebDesign"
    }
4.DELETE
webapi/boards/{boardId}

<professor>
1.GET
webapi/professors/
webapi/professors/{professorId}

2.POST
webapi/professors/
body:
{
        "department": "InformationSystems",
        "firstName": "Yuchen",
        "joiningDate": "2016-10-24 21:59:06",
        "lastName": "Wang",
        "professorId": "wang.yuchen3"
    }
3.PUT
webapi/professors/{professorId}
{
        "department": "IS"
}
4.DELETE
webapi/professors/{professorId}
