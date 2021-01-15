from classes import *

window = GUI()

buttonListStudent = Button(text="SELECT STUDENT LIST", command=window.studentList)
window.configButtonXLS(buttonListStudent)

buttonReports = Button(text="SELECT REPORTS FOLDER", command=window.reports)
window.configButtonFolder(buttonReports)

buttonAnswers = Button(text="SELECT ANSWERS KEY FOLDER", command=window.answers)
window.configButtonFolder(buttonAnswers)

buttonStart = Button(text="START", command=window.startProcess)
window.configButtonStart(buttonStart)

window.compileWindow()