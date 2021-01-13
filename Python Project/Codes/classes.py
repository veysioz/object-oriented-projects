import xlrd
import re
from openpyxl.styles import Font, Alignment
import csv
import glob
from openpyxl import Workbook


class Student:
    def __init__(self, no, id_number, name, surname):
        self.__no = no
        self.__id = id_number
        self.__name = name
        self.__surname = surname
        self.__polls = []

    def get_no(self):
        return self.__no

    def get_id(self):
        return self.__id

    def get_name(self):
        return self.__name

    def get_surname(self):
        return self.__surname

    def get_polls(self):
        return self.__polls


class StudentList:
    def __init__(self, file_name):
        self.__students = []
        book = xlrd.open_workbook(file_name)
        self.sheet = book.sheet_by_index(0)
        self.__generate_students()

    def __generate_students(self):
        no = 1
        for i in range(self.sheet.col(0).__len__()):
            row = self.sheet.row(i)
            if re.match(r'^-?\d+(?:\.\d+)?$', str(row.__getitem__(1).value)):
                self.__students.append(Student(no, row.__getitem__(2).value,
                                               row.__getitem__(4).value, row.__getitem__(7).value))
                no += 1

    def get_student(self, name_surname):
        searched = None
        similarity = 0.0
        name_surname = str(name_surname).replace('İ', 'i').replace('I', 'ı').lower().replace(" ", "")
        for student in self.__students:
            s = (student.get_name() + student.get_surname()).replace('İ', 'i').replace('I', 'ı')
            s = s.lower().replace(" ", "")
            max_length = max(name_surname.__len__(), s.__len__())
            if name_surname.__len__() >= s.__len__():
                min_name = s
                max_name = name_surname
            else:
                min_name = name_surname
                max_name = s
            counter = 0
            for i in range(min_name.__len__() - 2):
                if max_name.__contains__(min_name[i] + min_name[i+1] + min_name[i+2]):
                    counter += 1
            if counter / max_length > similarity:
                similarity = counter / max_length
                searched = student
        return searched

    def get_students(self):
        return self.__students


class Results:
    def __init__(self, student_list):
        self.__book = Workbook()
        self.__student_list = student_list
        self.__sheet_num = 0

    def __write_students(self, sheet):
        self.column_title(sheet, 'A', "NO", 5)
        self.column_title(sheet, 'B', "ID", 12)
        self.column_title(sheet, 'C', "Name", 20)
        self.column_title(sheet, 'D', "Surname", 20)
        no = 2
        for student in self.__student_list:
            self.add_cell(sheet, 'A', no, student.get_no())
            self.add_cell(sheet, 'B', no, student.get_id())
            self.add_cell(sheet, 'C', no, student.get_name())
            self.add_cell(sheet, 'D', no, student.get_surname())
            no += 1

    def new_sheet(self, sheet_name):
        if self.__sheet_num == 0:
            sheet = self.__book.active
            self.__sheet_num += 1
        else:
            sheet = self.__book.create_sheet(sheet_name, self.__sheet_num)
            self.__sheet_num += 1
        sheet.title = sheet_name
        self.__write_students(sheet)

    def column_title(self, sheet, column, column_title, column_width):
        sheet[column + '1'].font = Font(bold=True)
        sheet[column + '1'].alignment = Alignment(horizontal='left')
        sheet[column + '1'] = column_title
        sheet.column_dimensions[column].width = column_width

    def add_cell(self, sheet, column, row, data):
        sheet[column + str(row)].alignment = Alignment(horizontal='left')
        sheet[column + str(row)] = data

    def save_book(self):
        self.__book.save("results.xlsx")

    def get_book(self):
        return self.__book


class Question:
    def __init__(self, question_text, answer_text):
        self.__question_text = question_text
        self.__answer_text = answer_text

    def get_question(self):
        return self.__question_text

    def get_answer(self):
        return self.__answer_text


class Poll:
    def __init__(self, questions):
        self.__questions = questions
        self.__poll_name = ""

    def get_questions(self):
        return self.__questions

    def get_poll_name(self):
        return self.__poll_name

    def set_poll_name(self, poll_name):
        self.__poll_name = poll_name


class Reports:
    def __init__(self, file_path, student_list):
        self.__file_path = file_path
        self.__student_list = student_list

    def read_reports(self):
        files = glob.glob(self.__file_path + "/*.csv")
        for file in files:
            with open(file, encoding="utf8") as file_name:
                reader = csv.reader(file_name)
                for line in reader:
                    if line.__getitem__(0).isdigit() is not True:
                        continue
                    student = self.__student_list.get_student(line[1])
                    questions = []
                    i = 4
                    while i < line.__len__():
                        if line[i].replace(" ", "") != "":
                            questions.append(Question(line[i], line[i+1]))
                        i += 2
                    student.get_polls().append(Poll(questions))


class Attendance:
    def __init__(self, student_list, results):
        self.__student_list = student_list
        self.__results = results
        self.__results.new_sheet("Attendance")
        results.column_title(results.get_book()["Attendance"], 'E', 'Number of Attendance', 25)
        results.column_title(results.get_book()["Attendance"], 'F', 'Attendance Rate', 25)
        results.column_title(results.get_book()["Attendance"], 'G', 'Attendance Percentage', 25)

    def __get_total_lessons_number(self):
        total_lessons_number = 0
        for student in self.__student_list:
            lessons_number = 0
            for poll in student.get_polls():
                if poll.get_questions()[0].get_question() == "Are you attending this lecture?":
                    lessons_number += 1
                    if lessons_number > total_lessons_number:
                        total_lessons_number = lessons_number
        return total_lessons_number

    def add_attendance(self):
        total_lessons_number = self.__get_total_lessons_number()
        row = 2
        for student in self.__student_list:
            attendance_number = 0
            for poll in student.get_polls():
                if poll.get_questions()[0].get_question() == "Are you attending this lecture?":
                    attendance_number += 1
            self.__results.add_cell(self.__results.get_book()["Attendance"], 'E', row, attendance_number)
            self.__results.add_cell(self.__results.get_book()["Attendance"], 'F', row,
                                    float(attendance_number) / total_lessons_number)
            self.__results.add_cell(self.__results.get_book()["Attendance"], 'G', row,
                                    str(int((float(attendance_number) / total_lessons_number) * 100)) + " %")
            row += 1


class AnswerKeys:
    def __init__(self, file_path):
        self.__file_path = file_path

    def get_answer_keys(self):
        files = glob.glob(self.__file_path + "/*.csv")
        answer_keys = []
        questions = None
        for file in files:
            with open(file, encoding="utf8") as file_name:
                reader = csv.reader(file_name)
                for line in reader:
                    if line.__len__() == 1:
                        questions = []
                        poll = Poll(questions)
                        poll.set_poll_name(line[0])
                        answer_keys.append(poll)
                    else:
                        questions.append(Question(line[0], line[1]))
        return answer_keys


class CheckAnswers:
    def __init__(self, student_list, results, answer_keys):
        self.__student_list = student_list
        self.__results = results
        self.__answer_keys = answer_keys

    def check(self):
        for answer_key in self.__answer_keys:
            self.add_sheet(answer_key.get_poll_name(), len(answer_key.get_questions()))
            for student in self.__student_list:
                for poll in student.get_polls():
                    if self.poll_control(answer_key.get_questions(), poll.get_questions()):
                        chr_number = ord('D')
                        for true_answer, student_answer in zip(answer_key.get_questions(), poll.get_questions()):
                            chr_number += 1
                            self.__results.add_cell(self.__results.get_book()[answer_key.get_poll_name()],
                                                    chr(chr_number), student.get_no()+1,
                                                    int(true_answer.get_answer() == student_answer.get_answer()))

    def add_sheet(self, poll_name, questions_number):
        self.__results.new_sheet(poll_name)
        chr_number = ord('D')
        print()
        for i in range(questions_number):
            chr_number += 1
            self.__results.column_title(self.__results.get_book()[poll_name], chr(chr_number),
                                        'Q'+str(chr_number - 68), 5)

    def poll_control(self, questions_a, questions_b):
        if len(questions_a) != len(questions_b):
            return False
        for question_a, question_b in zip(questions_a, questions_b):
            if question_a.get_question() != question_b.get_question():
                return False
        return True
