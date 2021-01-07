import tkinter as tk
import xlrd
import xlwt


class Student:
    def __init__(self, no, id, name, surname):
        self.no = no
        self.id = id
        self.name = name
        self.surname = surname


class GUI:
    def __init__(self):
        root = tk.Tk()
        root.resizable(False, False)
        root.geometry("700x700")
        root.title("Poll Statistics")
        root.mainloop()


class ClassList:
    def __init__(self):
        book = xlrd.open_workbook('CES3063_Fall2020_rptSinifListesi.XLS')
        self.sheet = book.sheet_by_index(0)


class Output:
    def __init__(self):
        self.book = xlwt.Workbook()
        self.sheet = self.book.add_sheet('Output')
        self.style = xlwt.easyxf('align: horiz left;')
        self.title_style = xlwt.easyxf('font: bold on; align: horiz left;')
        self.sheet.col(0).width = 1250
        self.sheet.col(1).width = 3500
        self.sheet.col(2).width = 5000
        self.sheet.col(3).width = 5000

    def write(self, row, column, data):
        self.sheet.write(row, column, data, self.style)

    def write_title(self, row, column, data):
        self.sheet.write(row, column, data, self.title_style)

    def save(self):
        self.book.save('output.XLS')
