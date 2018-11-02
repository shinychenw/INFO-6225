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
	"firstName":"Yuchen",
	"lastName":"Wang"
}
3.PUT
webapi/students/{studentId}
{
	"firstName":"Yuchen",
	"lastName":"Wang"
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
	"courseName":"IS"}
3.PUT
webapi/ courses /{courseId}
{
	"courseName":"IS"
}
4.DELETE
webapi/ courses /{courseId}

<lecture>
1.GET
webapi/ courses /{courseId}/lectures
webapi/ courses /{courseId}/lectures/{lectureId}

2.POST
webapi/ courses /{courseId}/lectures
body:
{
	"notes":" notes ",
	"associatedMaterial":" associatedMaterial "
}

3.DELETE
webapi/ courses /{courseId}/lectures/{lectureId}

<program>
1.GET
webapi/programs/
webapi/programs/{programId}

2.POST
webapi/programs/
body:
{
	"programName":"IS"
}
3.PUT
webapi/programs/{programId}
{
	"programName":"IS"
}
4.DELETE
webapi/programs/{programId}

<professor>
1.GET
webapi/professors/
webapi/professors/{professorId}

2.POST
webapi/professors/
body:
{
	"firstName":"Yufei",
"lastName":"Gao"
}
3.PUT
webapi/professors/{professorId}
{
"firstName":"Yufei",
"lastName":"Gao"
}
4.DELETE
webapi/professors/{professorId}
