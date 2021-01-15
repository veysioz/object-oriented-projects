from classes import *

window = GUI()

buttonListStudent = Button(text="SELECT STUDENT LIST", command=window.studentList)
window.configButtonXLS(buttonListStudent)

buttonReports = Button(text="SELECT REPORTS FOLDER", command=window.reports)
window.configButtonFolder(buttonReports)

buttonAnswers = Button(text="SELECT ANSWERS KEY FOLDER", command=window.answers)
window.configButtonFolder(buttonAnswers)

window.compileWindow()