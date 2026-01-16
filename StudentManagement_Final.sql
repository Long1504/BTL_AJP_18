CREATE DATABASE StudentManagement_Final;

USE StudentManagement_Final;

CREATE TABLE Khoa (
	maKhoa CHAR(10) NOT NULL PRIMARY KEY,
    tenKhoa NVARCHAR(100) NOT NULL
);

CREATE TABLE Lop (
	maLop CHAR(10) NOT NULL PRIMARY KEY,
    tenLop NVARCHAR(100) NOT NULL,
    maKhoa CHAR(10) NOT NULL,
    CONSTRAINT FK_Lop_Khoa FOREIGN KEY(maKhoa) REFERENCES Khoa(maKhoa)
);

CREATE TABLE SinhVien (
	maSV CHAR(10) NOT NULL PRIMARY KEY,
    hoVaTen NVARCHAR(100) NOT NULL,
    gioiTinh NVARCHAR(10) NOT NULL,
    ngaySinh DATE,
    queQuan NVARCHAR(100),
    soDienThoai CHAR(10),
    email VARCHAR(100),
    matKhau VARCHAR(50) NOT NULL,
    phanQuyen CHAR(10) NOT NULL,
    maLop CHAR(10) NOT NULL,
    maKhoa CHAR(10) NOT NULL,
    CONSTRAINT FK_SinhVien_Lop FOREIGN KEY(maLop) REFERENCES Lop(maLop),
    CONSTRAINT FK_SinhVien_Khoa FOREIGN KEY(maKhoa) REFERENCES Khoa(maKhoa)
);

CREATE TABLE GiangVien (
	maGV CHAR(10) NOT NULL PRIMARY KEY,
    hoVaTen NVARCHAR(100) NOT NULL,
    gioiTinh NVARCHAR(10) NOT NULL,
    ngaySinh DATE,
    queQuan NVARCHAR(100),
    soDienThoai CHAR(10),
    email VARCHAR(100),
    matKhau VARCHAR(50) NOT NULL,
    phanQuyen CHAR(10) NOT NULL,
    maKhoa CHAR(10) NOT NULL,
    CONSTRAINT FK_GiangVien_Khoa FOREIGN KEY(maKhoa) REFERENCES Khoa(maKhoa)
);

CREATE TABLE HocPhan (
	maHP CHAR(10) NOT NULL PRIMARY KEY,
    tenHP NVARCHAR(100) NOT NULL,
    soTC INT NOT NULL,
    ngayHoc NVARCHAR(50),
    caHoc VARCHAR(50),
    phongHoc VARCHAR(50),
    maGV CHAR(10) NOT NULL,
    CONSTRAINT FK_HocPhan_GiangVien FOREIGN KEY(maGV) REFERENCES GiangVien(maGV)
);

CREATE TABLE DiemHocPhan (
    maHP CHAR(10) NOT NULL,
    maSV CHAR(10) NOT NULL,
    diem1 DOUBLE DEFAULT NULL,
    diem2 DOUBLE DEFAULT NULL,
    diemThi DOUBLE DEFAULT NULL,
    diemTongKet10 DOUBLE GENERATED ALWAYS AS (ROUND((diem1 * 0.2) + (diem2 * 0.2) + (diemThi * 0.6), 2)) STORED,
    diemTongKet4 DOUBLE GENERATED ALWAYS AS (
        CASE
            WHEN diemTongKet10 >= 8.5 THEN 4.0
            WHEN diemTongKet10 >= 7.7 THEN 3.5
            WHEN diemTongKet10 >= 7.0 THEN 3.0
            WHEN diemTongKet10 >= 6.2 THEN 2.5
            WHEN diemTongKet10 >= 5.5 THEN 2
            WHEN diemTongKet10 >= 4.7 THEN 1.5
            WHEN diemTongKet10 >= 4.0 THEN 1.0
            ELSE 0
        END
    ) STORED,
    diemChu VARCHAR(2) GENERATED ALWAYS AS (
        CASE
            WHEN diemTongKet10 >= 8.5 THEN 'A'
            WHEN diemTongKet10 >= 7.7 THEN 'B+'
            WHEN diemTongKet10 >= 7.0 THEN 'B'
            WHEN diemTongKet10 >= 6.2 THEN 'C+'
            WHEN diemTongKet10 >= 5.5 THEN 'C'
            WHEN diemTongKet10 >= 4.7 THEN 'D+'
            WHEN diemTongKet10 >= 4.0 THEN 'D'
            ELSE 'F'
        END
    ) STORED,
    xepLoai NVARCHAR(20) GENERATED ALWAYS AS (
        CASE
            WHEN diemTongKet10 >= 8.5 THEN 'Giỏi'
            WHEN diemTongKet10 >= 7.0 THEN 'Khá'
            WHEN diemTongKet10 >= 5.5 THEN 'Trung bình'
            WHEN diemTongKet10 >= 4.0 THEN 'Trung bình yếu'
            ELSE 'Kém'
        END
    ) STORED,
    PRIMARY KEY (maHP, maSV)
);

CREATE TABLE ThoiKhoaBieu (
	maSV CHAR(10) NOT NULL,
    maHP CHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(maSV, maHP),
    CONSTRAINT FK_ThoiKhoaBieu_SinhVien FOREIGN KEY(maSV) REFERENCES SinhVien(maSV),
    CONSTRAINT FK_ThoiKhoaBieu_HocPhan FOREIGN KEY(maHP) REFERENCES HocPhan(maHP)
);

CREATE TABLE DanhGia (
	maDG INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    noiDung NVARCHAR(255) NOT NULL,
    thoiGianTao VARCHAR(50),
    trangThai NVARCHAR(50) DEFAULT 'Chưa xem'
);

CREATE TABLE QuanTri (
	maQT CHAR(10) NOT NULL PRIMARY KEY,
    hoVaTen NVARCHAR(100) NOT NULL,
    matKhau VARCHAR(50) NOT NULL,
    phanQuyen CHAR(10) NOT NULL
);

INSERT INTO Khoa(maKhoa, tenKhoa)
VALUES
('CNTT', N'Công nghệ thông tin'),
('KTKT', N'Kế toán - Kiểm toán'),
('QLKD', N'Quản lý kinh doanh');

INSERT INTO Lop(maLop, tenLop, maKhoa)
VALUES
('CNTT01', N'Công nghệ thông tin 01', 'CNTT'),
('CNTT02', N'Công nghệ thông tin 02', 'CNTT'),
('HTTT01', N'Hệ thống thông tin 01', 'CNTT'),
('HTTT02', N'Hệ thống thông tin 02', 'CNTT');

INSERT INTO SinhVien(maSV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maLop, maKhoa)
VALUES
-- CNTT01
('SV00000001', N'Nguyễn Thị Anh', N'Nữ', '2004/6/7', N'Hà Nội', '0123456789', 'nta0706@gmail.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000002', N'Phạm Việt Long', N'Nam', '2004/4/15', N'Tuyên Quang', '033346178', 'pvl1504@gmail.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000003', N'Lê Minh Tuấn', N'Nam', '2004/05/10', N'Hà Nội', '0323456790', 'lmt1005@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000004', N'Nguyễn Thanh Bình', N'Nam', '2004/07/20', N'Vĩnh Phúc', '0333456791', 'ntb2007@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000005', N'Vũ Minh Anh', N'Nữ', '2004/08/05', N'Bắc Ninh', '0343456792', 'vma0508@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000006', N'Trần Mai Linh', N'Nữ', '2004/09/18', N'Quảng Ninh', '0353456793', 'tml1809@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000007', N'Hoàng Minh Tuấn', N'Nam', '2004/10/25', N'Hà Nội', '0363456794', 'hmt2510@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000008', N'Phan Thiên Anh', N'Nữ', '2004/06/22', N'Bắc Giang', '0373456795', 'pta2206@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000009', N'Nguyễn Thị Thu', N'Nữ', '2004/11/03', N'Bình Dương', '0383456796', 'ntt0311@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000010', N'Lê Quốc Huy', N'Nam', '2004/03/12', N'Hà Nam', '0393456797', 'lqh1203@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000011', N'Phạm Ngọc Minh', N'Nam', '2004/12/15', N'Nam Định', '0312456798', 'pnm1512@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000012', N'Trần Minh Tâm', N'Nam', '2004/05/25', N'Hải Phòng', '0321456799', 'tmt2505@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000013', N'Nguyễn Thị Ngọc', N'Nữ', '2004/07/30', N'Hưng Yên', '0331456700', 'ntn3007@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000014', N'Hoàng Anh Tuấn', N'Nam', '2004/02/14', N'Lào Cai', '0341456701', 'hat1402@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000015', N'Phạm Mai Chi', N'Nữ', '2004/01/25', N'Hà Nội', '0351456702', 'pmc2501@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000016', N'Lê Văn Hùng', N'Nam', '2004/06/30', N'Quảng Bình', '0361456703', 'lvh3006@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000017', N'Trần Thị Hà', N'Nữ', '2004/03/15', N'Hà Tĩnh', '0371456704', 'tth1503@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000018', N'Nguyễn Thanh Phong', N'Nam', '2004/08/22', N'Nghệ An', '0381456705', 'ntp2208@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000019', N'Hoàng Mai Phương', N'Nữ', '2004/09/18', N'Hải Dương', '0391456706', 'hmp1809@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
('SV00000020', N'Phạm Văn Dũng', N'Nam', '2004/12/20', N'Thái Bình', '0310456707', 'pvd2012@student.com', 'SV@123', 'SV', 'CNTT01', 'CNTT'),
-- CNTT02
('SV00000021', N'Lê Minh Quang', N'Nam', '2004/01/05', N'Hà Nội', '0320000001', 'lmq0501@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000022', N'Nguyễn Thị Hạnh', N'Nữ', '2004/02/14', N'Hải Phòng', '0330000002', 'nth1402@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000023', N'Trần Văn Hùng', N'Nam', '2004/03/21', N'Nam Định', '0340000003', 'tvh2103@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000024', N'Phạm Thị Hoa', N'Nữ', '2004/04/10', N'Hưng Yên', '0350000004', 'pth1004@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000025', N'Vũ Hồng Sơn', N'Nam', '2004/05/15', N'Quảng Ninh', '0360000005', 'vhs1505@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000026', N'Ngô Thị Lan', N'Nữ', '2004/06/22', N'Nghệ An', '0370000006', 'ntl2206@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000027', N'Hoàng Văn Huy', N'Nam', '2004/07/01', N'Hà Tĩnh', '0380000007', 'hvh0107@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000028', N'Nguyễn Mai Anh', N'Nữ', '2004/08/18', N'Bắc Ninh', '0390000008', 'nma1808@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000029', N'Lê Văn Hải', N'Nam', '2004/09/30', N'Thái Bình', '0310000009', 'lvh3009@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000030', N'Phạm Ngọc Ánh', N'Nữ', '2004/10/12', N'Hà Giang', '0320000010', 'pna1210@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000031', N'Trần Anh Dũng', N'Nam', '2004/11/05', N'Yên Bái', '0330000011', 'tad0511@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000032', N'Nguyễn Văn Tú', N'Nam', '2004/12/25', N'Lào Cai', '0340000012', 'nvt2512@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000033', N'Hoàng Thị Phương', N'Nữ', '2004/01/18', N'Hòa Bình', '0350000013', 'htp1801@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000034', N'Lê Thị Thu', N'Nữ', '2004/02/28', N'Sơn La', '0360000014', 'ltt2802@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000035', N'Phạm Minh Quân', N'Nam', '2004/03/10', N'Điện Biên', '0370000015', 'pmq1003@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000036', N'Vũ Văn An', N'Nam', '2004/04/15', N'Lạng Sơn', '0380000016', 'vva1504@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000037', N'Nguyễn Văn Bình', N'Nam', '2004/05/20', N'Cao Bằng', '0390000017', 'nvb2005@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000038', N'Hoàng Mai Linh', N'Nữ', '2004/06/30', N'Tuyên Quang', '0310000018', 'hml3006@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000039', N'Trần Hải Nam', N'Nam', '2004/07/12', N'Lai Châu', '0320000019', 'thn1207@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
('SV00000040', N'Phạm Minh Khang', N'Nam', '2004/08/22', N'Phú Thọ', '0330000020', 'pmk2208@student.com', 'SV@123', 'SV', 'CNTT02', 'CNTT'),
-- HTTT01
('SV00000041', N'Nguyễn Văn Long', N'Nam', '2004/01/02', N'Hà Nội', '0340000021', 'nvl0201@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000042', N'Lê Thị Thanh', N'Nữ', '2004/02/05', N'Hải Phòng', '0350000022', 'ltt0502@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000043', N'Trần Huy Hoàng', N'Nam', '2004/03/07', N'Nam Định', '0360000023', 'thh0703@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000044', N'Phạm Thị Hương', N'Nữ', '2004/04/14', N'Hưng Yên', '0370000024', 'pth1404@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000045', N'Vũ Minh Hải', N'Nam', '2004/05/22', N'Quảng Ninh', '0380000025', 'vmh2205@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000046', N'Ngô Thị Bích', N'Nữ', '2004/06/01', N'Nghệ An', '0390000026', 'ntb0106@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000047', N'Hoàng Văn Tú', N'Nam', '2004/07/10', N'Hà Tĩnh', '0310000027', 'hvt1007@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000048', N'Nguyễn Thanh Nga', N'Nữ', '2004/08/18', N'Bắc Ninh', '0320000028', 'ntn1808@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000049', N'Lê Minh Hùng', N'Nam', '2004/09/25', N'Thái Bình', '0330000029', 'lmh2509@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000050', N'Phạm Văn Kiên', N'Nam', '2004/10/03', N'Hà Giang', '0340000030', 'pvk0310@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000051', N'Trần Quốc Anh', N'Nam', '2004/11/20', N'Yên Bái', '0350000031', 'tqa2011@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000052', N'Nguyễn Thị Ngọc', N'Nữ', '2004/12/12', N'Lào Cai', '0360000032', 'ntn1212@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000053', N'Hoàng Hải Yến', N'Nữ', '2004/01/28', N'Hòa Bình', '0370000033', 'hhy2801@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000054', N'Lê Văn Phúc', N'Nam', '2004/02/09', N'Sơn La', '0380000034', 'lvp0902@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000055', N'Phạm Ngọc Anh', N'Nữ', '2004/03/30', N'Điện Biên', '0390000035', 'pna3003@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000056', N'Vũ Hải Nam', N'Nam', '2004/04/12', N'Lạng Sơn', '0310000036', 'vhn1204@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000057', N'Nguyễn Văn Đức', N'Nam', '2004/05/19', N'Cao Bằng', '0320000037', 'nvd1905@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000058', N'Hoàng Thanh Mai', N'Nữ', '2004/06/15', N'Tuyên Quang', '0330000038', 'htm1506@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000059', N'Trần Văn Khoa', N'Nam', '2004/07/08', N'Lai Châu', '0340000039', 'tvk0807@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
('SV00000060', N'Phạm Minh Tuấn', N'Nam', '2004/08/30', N'Phú Thọ', '0350000040', 'pmt3008@student.com', 'SV@123', 'SV', 'HTTT01', 'CNTT'),
-- HTTT02
('SV00000061', N'Nguyễn Văn Phong', N'Nam', '2004/01/05', N'Hà Nội', '0340000041', 'nvp0501@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000062', N'Lê Thị Thu', N'Nữ', '2004/02/14', N'Hải Phòng', '0350000042', 'ltt1402@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000063', N'Trần Minh Quân', N'Nam', '2004/03/21', N'Nghệ An', '0360000043', 'tmq2103@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000064', N'Phạm Thị Hoa', N'Nữ', '2004/04/12', N'Nam Định', '0370000044', 'pth1204@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000065', N'Hoàng Văn Khải', N'Nam', '2004/05/25', N'Quảng Ninh', '0380000045', 'hvk2505@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000066', N'Vũ Thị Thanh', N'Nữ', '2004/06/15', N'Hưng Yên', '0390000046', 'vtt1506@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000067', N'Ngô Văn Tú', N'Nam', '2004/07/03', N'Hà Tĩnh', '0310000047', 'nvt0307@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000068', N'Lê Thị Ngọc', N'Nữ', '2004/08/10', N'Bắc Ninh', '0320000048', 'ltn1008@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000069', N'Trần Văn Hải', N'Nam', '2004/09/08', N'Thái Bình', '0330000049', 'tvh0809@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000070', N'Nguyễn Quốc Anh', N'Nam', '2004/10/19', N'Hà Giang', '0340000050', 'nqa1910@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000071', N'Phạm Thanh Mai', N'Nữ', '2004/11/23', N'Yên Bái', '0350000051', 'ptm2311@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000072', N'Lê Văn Minh', N'Nam', '2004/12/16', N'Lào Cai', '0360000052', 'lvm1612@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000073', N'Nguyễn Thị Hương', N'Nữ', '2004/01/11', N'Hòa Bình', '0370000053', 'nth1101@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000074', N'Trần Văn Phúc', N'Nam', '2004/02/22', N'Sơn La', '0380000054', 'tvp2202@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000075', N'Lê Thanh Hùng', N'Nam', '2004/03/09', N'Điện Biên', '0390000055', 'lth0903@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000076', N'Vũ Văn Nam', N'Nam', '2004/04/05', N'Lạng Sơn', '0310000056', 'vvn0504@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000077', N'Nguyễn Thị Hoa', N'Nữ', '2004/05/17', N'Cao Bằng', '0320000057', 'nth1705@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000078', N'Trần Văn Khoa', N'Nam', '2004/06/13', N'Tuyên Quang', '0330000058', 'tvk1306@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000079', N'Phạm Minh Hải', N'Nam', '2004/07/22', N'Lai Châu', '0340000059', 'pmh2207@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT'),
('SV00000080', N'Lê Văn Tuấn', N'Nam', '2004/08/29', N'Phú Thọ', '0350000060', 'lvt2908@student.com', 'SV@123', 'SV', 'HTTT02', 'CNTT');

INSERT INTO GiangVien(maGV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maKhoa)
VALUES
-- CNTT
('GV001', N'Nguyễn Văn Mạnh', N'Nam', '1969/6/7', N'Hà Nội', '0912345678', 'nvm0706@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV002', N'Lê Thị Hằng', N'Nữ', '1970/03/15', N'Hà Nội', '0912345679', 'lth1503@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV003', N'Trần Văn Hùng', N'Nam', '1972/05/22', N'Hải Phòng', '0912345680', 'tvh2205@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV004', N'Phạm Quang Minh', N'Nam', '1980/08/10', N'Nam Định', '0912345681', 'pqm1008@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV005', N'Ngô Thị Lan', N'Nữ', '1975/02/28', N'Ninh Bình', '0912345682', 'ntl2802@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV006', N'Hoàng Văn Thắng', N'Nam', '1985/09/12', N'Quảng Ninh', '0912345683', 'hvt1209@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV007', N'Lê Minh Phương', N'Nữ', '1988/07/07', N'Hà Nam', '0912345684', 'lmp0707@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV008', N'Vũ Văn Hải', N'Nam', '1979/11/05', N'Thái Bình', '0912345685', 'vvh0511@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV009', N'Nguyễn Quốc Bảo', N'Nam', '1983/04/18', N'Tuyên Quang', '0912345686', 'nqb1804@gmail.com', 'GV@123', 'GV', 'CNTT'),
('GV010', N'Trần Thị Mai', N'Nữ', '1990/12/25', N'Bắc Giang', '0912345687', 'ttm2512@gmail.com', 'GV@123', 'GV', 'CNTT'),
-- KTKT
('GV011', N'Nguyễn Văn Tài', N'Nam', '1978/01/15', N'Hà Nội', '0912345688', 'nvt1501@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV012', N'Hoàng Thị Thu', N'Nữ', '1982/03/08', N'Hải Dương', '0912345689', 'htt0803@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV013', N'Phạm Văn Quân', N'Nam', '1975/06/20', N'Bắc Ninh', '0912345690', 'pvq2006@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV014', N'Trần Thị Ngọc', N'Nữ', '1980/11/30', N'Bắc Giang', '0912345691', 'ttn3011@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV015', N'Vũ Thị Bích', N'Nữ', '1990/05/12', N'Ninh Bình', '0912345692', 'vtb1205@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV016', N'Lê Minh Hoàng', N'Nam', '1983/02/18', N'Thái Nguyên', '0912345693', 'lmh1802@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV017', N'Ngô Văn Bình', N'Nam', '1979/08/25', N'Quảng Ninh', '0912345694', 'nvb2508@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV018', N'Phạm Thu Hằng', N'Nữ', '1987/04/10', N'Thái Bình', '0912345695', 'pth1004@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV019', N'Hoàng Văn Tùng', N'Nam', '1985/09/22', N'Hà Nam', '0912345696', 'hvt2209@gmail.com', 'GV@123', 'GV', 'KTKT'),
('GV020', N'Lê Thị Huyền', N'Nữ', '1992/07/17', N'Hòa Bình', '0912345697', 'lth1707@gmail.com', 'GV@123', 'GV', 'KTKT'),
-- QLKD
('GV021', N'Nguyễn Thị Mai', N'Nữ', '1981/03/14', N'Hà Nội', '0912345698', 'ntm1403@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV022', N'Phạm Văn Toàn', N'Nam', '1977/05/21', N'Hải Phòng', '0912345699', 'pvt2105@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV023', N'Lê Thị Phương', N'Nữ', '1985/07/08', N'Quảng Ninh', '0912345700', 'ltp0807@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV024', N'Hoàng Văn Khánh', N'Nam', '1980/09/02', N'Thái Bình', '0912345701', 'hvk0209@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV025', N'Trần Thu Hà', N'Nữ', '1990/11/19', N'Hòa Bình', '0912345702', 'tth1911@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV026', N'Ngô Văn Dũng', N'Nam', '1983/02/16', N'Ninh Bình', '0912345703', 'nvd1602@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV027', N'Phạm Thị Hương', N'Nữ', '1992/06/11', N'Tuyên Quang', '0912345704', 'pth1106@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV028', N'Vũ Minh Châu', N'Nữ', '1987/08/24', N'Hà Nam', '0912345705', 'vmc2408@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV029', N'Trần Văn Long', N'Nam', '1979/04/15', N'Nam Định', '0912345706', 'tvl1504@gmail.com', 'GV@123', 'GV', 'QLKD'),
('GV030', N'Nguyễn Văn Hiếu', N'Nam', '1991/12/05', N'Bắc Ninh', '0912345707', 'nvh0512@gmail.com', 'GV@123', 'GV', 'QLKD');

INSERT INTO HocPhan(maHP, tenHP, soTC, ngayHoc, caHoc, phongHoc, maGV)
VALUES
('HP001', N'Mạng máy tính', 3, 'Thứ 2', '1, 2, 3', '505-A9', 'GV001'),
('HP002', N'An ninh mạng', 3, 'Thứ 2', '4, 5, 6', '502-A9', 'GV001'),
('HP003', N'Thiết kế Web', 3, 'Thứ 3', '7, 8, 9', '402-A9', 'GV001'),
('HP004', N'Thực tập cơ sở nghành', 3, 'Thứ 4', '1, 2, 3', '306-A9', 'GV001'),

('HP005', N'Hệ thống cơ sở dữ liệu', 4, 'Thứ 5', '1, 2, 3', '304-A9', 'GV002'),
('HP006', N'Phân tích thiết kế phần mềm', 3, 'Thứ 6', '7, 8, 9', '208-A9', 'GV002'),
('HP007', N'Khoa học máy tính', 3, 'Thứ 2', '1, 2, 3', '507-A9', 'GV002'),
('HP008', N'Xử lý ảnh', 3, 'Thứ 5', '4, 5, 6', '503-A9', 'GV002'),

('HP009', N'Kỹ thuật lập trình', 3, 'Thứ 3', '1, 2, 3', '409-A9', 'GV003'),
('HP010', N'Phát triển ứng dụng di động', 3, 'Thứ 6', '4, 5, 6', '405-A9', 'GV003'),
('HP011', N'Kiến trúc máy tính', 3, 'Thứ 4', '1, 2, 3', '407-A9', 'GV003'),
('HP012', N'Phát triển phần mềm', 3, 'Thứ 7', '4, 5, 6', '406-A9', 'GV003'),

('HP013', N'Thiết kế đồ họa', 3, 'Thứ 2', '6, 7, 8', '501-A8', 'GV004'),
('HP014', N'Xử lý tín hiệu', 3, 'Thứ 5', '1, 2, 3', '509-A10', 'GV004'),
('HP015', N'Mạng không dây', 3, 'Thứ 3', '4, 5, 6', '510-A8', 'GV004'),
('HP016', N'Quản trị mạng', 3, 'Thứ 6', '7, 8, 9', '512-A8', 'GV004'),

('HP017', N'Điện tử số', 3, 'Thứ 4', '4, 5, 6', '514-A10', 'GV005'),
('HP018', N'Thiết kế cơ sở dữ liệu', 3, 'Thứ 7', '1, 2, 3', '515-A10', 'GV005'),
('HP019', N'Mạng máy tính nâng cao', 3, 'Thứ 3', '1, 2, 3', '507-A8', 'GV005'),
('HP020', N'An ninh mạng nâng cao', 3, 'Thứ 5', '4, 5, 6', '509-A8', 'GV005'),

('HP021', N'Xử lý tín hiệu số', 3, 'Thứ 2', '1, 2, 3', '501-A8', 'GV006'),
('HP022', N'Phát triển game', 3, 'Thứ 4', '7, 8, 9', '507-A10', 'GV006'),
('HP023', N'Kỹ thuật số', 3, 'Thứ 5', '1, 2, 3', '508-A10', 'GV006'),
('HP024', N'Thiết kế hệ thống', 3, 'Thứ 6', '4, 5, 6', '509-A9', 'GV006'),

('HP025', N'Thiết kế giao diện người dùng', 3, 'Thứ 3', '1, 2, 3', '510-A8', 'GV007'),
('HP026', N'Quản trị hệ thống', 3, 'Thứ 2', '4, 5, 6', '511-A9', 'GV007'),
('HP027', N'Thuật toán máy học', 3, 'Thứ 4', '7, 8, 9', '512-A9', 'GV007'),
('HP028', N'Kỹ thuật phần mềm', 3, 'Thứ 6', '1, 2, 3', '513-A9', 'GV007'),

('HP029', N'Mạng truyền thông', 3, 'Thứ 2', '7, 8, 9', '504-A8', 'GV008'),
('HP030', N'Máy học và trí tuệ nhân tạo', 3, 'Thứ 5', '4, 5, 6', '505-A9', 'GV008'),
('HP031', N'Xử lý tín hiệu thời gian thực', 3, 'Thứ 3', '1, 2, 3', '506-A8', 'GV008'),
('HP032', N'Thiết kế hệ thống phân tán', 3, 'Thứ 6', '7, 8, 9', '507-A10', 'GV008'),

('HP033', N'An toàn thông tin', 3, 'Thứ 4', '1, 2, 3', '508-A9', 'GV009'),
('HP034', N'Kỹ thuật mạng', 3, 'Thứ 2', '4, 5, 6', '509-A8', 'GV009'),
('HP035', N'Phát triển phần mềm di động', 3, 'Thứ 5', '1, 2, 3', '510-A9', 'GV009'),
('HP036', N'Mạng máy tính nâng cao', 3, 'Thứ 6', '7, 8, 9', '511-A10', 'GV009'),

('HP037', N'Điện tử viễn thông', 3, 'Thứ 3', '4, 5, 6', '512-A8', 'GV010'),
('HP038', N'Xử lý ảnh số', 3, 'Thứ 5', '7, 8, 9', '513-A9', 'GV010'),
('HP039', N'Phát triển phần mềm máy tính', 3, 'Thứ 2', '1, 2, 3', '514-A10', 'GV010'),
('HP040', N'Mạng và truyền thông không dây', 3, 'Thứ 7', '4, 5, 6', '515-A8', 'GV010');

INSERT INTO ThoiKhoaBieu(maSV, maHP)
VALUES
('SV00000001', 'HP001'),
('SV00000001', 'HP002'),
('SV00000001', 'HP003'),
('SV00000001', 'HP004'),
('SV00000001', 'HP005'),
('SV00000001', 'HP006'),
('SV00000001', 'HP007'),
('SV00000001', 'HP008'),
('SV00000001', 'HP009'),
('SV00000001', 'HP010'),

('SV00000002', 'HP001'),
('SV00000002', 'HP002'),
('SV00000002', 'HP003'),
('SV00000002', 'HP004'),
('SV00000002', 'HP005'),
('SV00000002', 'HP006'),
('SV00000002', 'HP007'),
('SV00000002', 'HP008'),
('SV00000002', 'HP009'),
('SV00000002', 'HP010'),

('SV00000003', 'HP001'),
('SV00000003', 'HP002'),
('SV00000003', 'HP003'),
('SV00000003', 'HP004'),
('SV00000003', 'HP005'),
('SV00000003', 'HP006'),
('SV00000003', 'HP007'),
('SV00000003', 'HP008'),
('SV00000003', 'HP009'),
('SV00000003', 'HP010'),

('SV00000004', 'HP001'),
('SV00000004', 'HP002'),
('SV00000004', 'HP003'),
('SV00000004', 'HP004'),
('SV00000004', 'HP005'),
('SV00000004', 'HP006'),
('SV00000004', 'HP007'),
('SV00000004', 'HP008'),
('SV00000004', 'HP009'),
('SV00000004', 'HP010'),

('SV00000005', 'HP001'),
('SV00000005', 'HP002'),
('SV00000005', 'HP003'),
('SV00000005', 'HP004'),
('SV00000005', 'HP005'),
('SV00000005', 'HP006'),
('SV00000005', 'HP007'),
('SV00000005', 'HP008'),
('SV00000005', 'HP009'),
('SV00000005', 'HP010'),

('SV00000006', 'HP001'),
('SV00000006', 'HP002'),
('SV00000006', 'HP003'),
('SV00000006', 'HP004'),
('SV00000006', 'HP005'),
('SV00000006', 'HP006'),
('SV00000006', 'HP007'),
('SV00000006', 'HP008'),
('SV00000006', 'HP009'),
('SV00000006', 'HP010'),

('SV00000007', 'HP001'),
('SV00000007', 'HP002'),
('SV00000007', 'HP003'),
('SV00000007', 'HP004'),
('SV00000007', 'HP005'),
('SV00000007', 'HP006'),
('SV00000007', 'HP007'),
('SV00000007', 'HP008'),
('SV00000007', 'HP009'),
('SV00000007', 'HP010'),

('SV00000008', 'HP001'),
('SV00000008', 'HP002'),
('SV00000008', 'HP003'),
('SV00000008', 'HP004'),
('SV00000008', 'HP005'),
('SV00000008', 'HP006'),
('SV00000008', 'HP007'),
('SV00000008', 'HP008'),
('SV00000008', 'HP009'),
('SV00000008', 'HP010'),

('SV00000009', 'HP001'),
('SV00000009', 'HP002'),
('SV00000009', 'HP003'),
('SV00000009', 'HP004'),
('SV00000009', 'HP005'),
('SV00000009', 'HP006'),
('SV00000009', 'HP007'),
('SV00000009', 'HP008'),
('SV00000009', 'HP009'),
('SV00000009', 'HP010'),

('SV00000010', 'HP001'),
('SV00000010', 'HP002'),
('SV00000010', 'HP003'),
('SV00000010', 'HP004'),
('SV00000010', 'HP005'),
('SV00000010', 'HP006'),
('SV00000010', 'HP007'),
('SV00000010', 'HP008'),
('SV00000010', 'HP009'),
('SV00000010', 'HP010'),

('SV00000011', 'HP001'),
('SV00000011', 'HP002'),
('SV00000011', 'HP003'),
('SV00000011', 'HP004'),
('SV00000011', 'HP005'),
('SV00000011', 'HP006'),
('SV00000011', 'HP007'),
('SV00000011', 'HP008'),
('SV00000011', 'HP009'),
('SV00000011', 'HP010'),

('SV00000012', 'HP001'),
('SV00000012', 'HP002'),
('SV00000012', 'HP003'),
('SV00000012', 'HP004'),
('SV00000012', 'HP005'),
('SV00000012', 'HP006'),
('SV00000012', 'HP007'),
('SV00000012', 'HP008'),
('SV00000012', 'HP009'),
('SV00000012', 'HP010'),

('SV00000013', 'HP001'),
('SV00000013', 'HP002'),
('SV00000013', 'HP003'),
('SV00000013', 'HP004'),
('SV00000013', 'HP005'),
('SV00000013', 'HP006'),
('SV00000013', 'HP007'),
('SV00000013', 'HP008'),
('SV00000013', 'HP009'),
('SV00000013', 'HP010'),

('SV00000014', 'HP001'),
('SV00000014', 'HP002'),
('SV00000014', 'HP003'),
('SV00000014', 'HP004'),
('SV00000014', 'HP005'),
('SV00000014', 'HP006'),
('SV00000014', 'HP007'),
('SV00000014', 'HP008'),
('SV00000014', 'HP009'),
('SV00000014', 'HP010'),

('SV00000015', 'HP001'),
('SV00000015', 'HP002'),
('SV00000015', 'HP003'),
('SV00000015', 'HP004'),
('SV00000015', 'HP005'),
('SV00000015', 'HP006'),
('SV00000015', 'HP007'),
('SV00000015', 'HP008'),
('SV00000015', 'HP009'),
('SV00000015', 'HP010'),

('SV00000016', 'HP001'),
('SV00000016', 'HP002'),
('SV00000016', 'HP003'),
('SV00000016', 'HP004'),
('SV00000016', 'HP005'),
('SV00000016', 'HP006'),
('SV00000016', 'HP007'),
('SV00000016', 'HP008'),
('SV00000016', 'HP009'),
('SV00000016', 'HP010'),

('SV00000017', 'HP001'),
('SV00000017', 'HP002'),
('SV00000017', 'HP003'),
('SV00000017', 'HP004'),
('SV00000017', 'HP005'),
('SV00000017', 'HP006'),
('SV00000017', 'HP007'),
('SV00000017', 'HP008'),
('SV00000017', 'HP009'),
('SV00000017', 'HP010'),

('SV00000018', 'HP001'),
('SV00000018', 'HP002'),
('SV00000018', 'HP003'),
('SV00000018', 'HP004'),
('SV00000018', 'HP005'),
('SV00000018', 'HP006'),
('SV00000018', 'HP007'),
('SV00000018', 'HP008'),
('SV00000018', 'HP009'),
('SV00000018', 'HP010'),

('SV00000019', 'HP001'),
('SV00000019', 'HP002'),
('SV00000019', 'HP003'),
('SV00000019', 'HP004'),
('SV00000019', 'HP005'),
('SV00000019', 'HP006'),
('SV00000019', 'HP007'),
('SV00000019', 'HP008'),
('SV00000019', 'HP009'),
('SV00000019', 'HP010'),

('SV00000020', 'HP001'),
('SV00000020', 'HP002'),
('SV00000020', 'HP003'),
('SV00000020', 'HP004'),
('SV00000020', 'HP005'),
('SV00000020', 'HP006'),
('SV00000020', 'HP007'),
('SV00000020', 'HP008'),
('SV00000020', 'HP009'),
('SV00000020', 'HP010');

INSERT INTO DiemHocPhan(maHP, maSV, diem1, diem2, diemThi)
VALUES
('HP001', 'SV00000001', 8.5, 7.0, 9.0),
('HP002', 'SV00000001', 7.0, 6.5, 8.0),
('HP003', 'SV00000001', 9.0, 8.0, 7.5),
('HP004', 'SV00000001', 8.0, 7.5, 9.0),
('HP005', 'SV00000001', 7.5, 7.0, 8.5),
('HP006', 'SV00000001', 9.5, 8.5, 9.0),
('HP007', 'SV00000001', 6.0, 6.5, 7.0),
('HP008', 'SV00000001', 8.0, 8.0, 8.5),
('HP009', 'SV00000001', 7.5, 7.0, 8.0),
('HP010', 'SV00000001', 8.5, 9.0, 8.5),

('HP001', 'SV00000002', 8.0, 7.5, 8.0),
('HP002', 'SV00000002', 7.5, 8.0, 7.5),
('HP003', 'SV00000002', 6.5, 7.0, 8.0),
('HP004', 'SV00000002', 8.5, 8.0, 8.5),
('HP005', 'SV00000002', 7.0, 6.5, 7.5),
('HP006', 'SV00000002', 9.0, 8.5, 8.5),
('HP007', 'SV00000002', 8.0, 7.5, 8.5),
('HP008', 'SV00000002', 7.5, 7.0, 8.0),
('HP009', 'SV00000002', 8.5, 8.0, 9.0),
('HP010', 'SV00000002', 9.0, 9.5, 8.5),

('HP001', 'SV00000003', 8.0, 7.0, 7.5),
('HP002', 'SV00000003', 7.5, 8.0, 8.0),
('HP003', 'SV00000003', 8.5, 7.5, 9.0),
('HP004', 'SV00000003', 7.0, 6.5, 8.0),
('HP005', 'SV00000003', 8.0, 7.5, 7.5),
('HP006', 'SV00000003', 8.5, 9.0, 8.0),
('HP007', 'SV00000003', 6.5, 7.0, 7.5),
('HP008', 'SV00000003', 8.0, 8.5, 8.5),
('HP009', 'SV00000003', 7.0, 6.0, 8.0),
('HP010', 'SV00000003', 8.5, 7.5, 9.0),

('HP001', 'SV00000004', 9.0, 8.5, 9.5),
('HP002', 'SV00000004', 7.0, 7.5, 8.0),
('HP003', 'SV00000004', 6.5, 7.0, 8.5),
('HP004', 'SV00000004', 8.0, 8.5, 9.0),
('HP005', 'SV00000004', 7.5, 7.0, 8.0),
('HP006', 'SV00000004', 8.5, 8.0, 9.0),
('HP007', 'SV00000004', 8.0, 7.5, 8.0),
('HP008', 'SV00000004', 9.0, 8.5, 9.5),
('HP009', 'SV00000004', 7.5, 7.0, 7.5),
('HP010', 'SV00000004', 8.5, 9.0, 8.5),

('HP001', 'SV00000005', 8.0, 7.5, 8.5),
('HP002', 'SV00000005', 7.5, 8.0, 7.5),
('HP003', 'SV00000005', 8.0, 7.5, 9.0),
('HP004', 'SV00000005', 7.0, 6.5, 8.0),
('HP005', 'SV00000005', 8.5, 8.0, 9.0),
('HP006', 'SV00000005', 8.0, 7.0, 7.5),
('HP007', 'SV00000005', 9.0, 8.5, 9.0),
('HP008', 'SV00000005', 7.5, 7.0, 8.0),
('HP009', 'SV00000005', 8.5, 8.0, 8.5),
('HP010', 'SV00000005', 9.0, 8.5, 9.5),

('HP001', 'SV00000006', 8.5, 8.0, 8.5),
('HP002', 'SV00000006', 7.0, 7.5, 8.0),
('HP003', 'SV00000006', 6.5, 7.0, 7.5),
('HP004', 'SV00000006', 8.5, 8.0, 8.5),
('HP005', 'SV00000006', 7.0, 7.5, 7.5),
('HP006', 'SV00000006', 8.0, 8.5, 8.5),
('HP007', 'SV00000006', 7.5, 7.0, 8.0),
('HP008', 'SV00000006', 9.0, 8.5, 9.0),
('HP009', 'SV00000006', 8.5, 7.5, 9.0),
('HP010', 'SV00000006', 7.0, 7.5, 8.0),

('HP001', 'SV00000007', 9.0, 8.5, 9.0),
('HP002', 'SV00000007', 8.0, 7.5, 8.5),
('HP003', 'SV00000007', 7.5, 8.0, 8.0),
('HP004', 'SV00000007', 8.5, 8.0, 8.5),
('HP005', 'SV00000007', 7.0, 6.5, 7.5),
('HP006', 'SV00000007', 8.0, 7.5, 8.0),
('HP007', 'SV00000007', 9.5, 9.0, 9.5),
('HP008', 'SV00000007', 8.0, 7.5, 8.5),
('HP009', 'SV00000007', 8.5, 8.0, 9.0),
('HP010', 'SV00000007', 9.0, 9.0, 9.5),

('HP001', 'SV00000008', 5.5, 6.0, 6.5),
('HP002', 'SV00000008', 6.0, 5.5, 6.0),
('HP003', 'SV00000008', 7.0, 6.5, 7.0),
('HP004', 'SV00000008', 4.5, 5.0, 6.0),
('HP005', 'SV00000008', 5.0, 5.5, 5.5),
('HP006', 'SV00000008', 6.5, 6.0, 7.0),
('HP007', 'SV00000008', 4.0, 4.5, 5.0),
('HP008', 'SV00000008', 5.5, 6.0, 5.5),
('HP009', 'SV00000008', 6.0, 5.5, 6.5),
('HP010', 'SV00000008', 6.5, 6.0, 6.0),

('HP001', 'SV00000009', 7.5, 6.5, 8.0),
('HP002', 'SV00000009', 8.0, 7.5, 8.5),
('HP003', 'SV00000009', 7.0, 6.5, 7.5),
('HP004', 'SV00000009', 6.0, 5.5, 6.5),
('HP005', 'SV00000009', 7.0, 6.0, 7.0),
('HP006', 'SV00000009', 6.5, 7.0, 6.5),
('HP007', 'SV00000009', 5.5, 6.0, 5.5),
('HP008', 'SV00000009', 7.5, 8.0, 7.5),
('HP009', 'SV00000009', 6.0, 5.5, 6.0),
('HP010', 'SV00000009', 8.0, 7.5, 7.5),

('HP001', 'SV00000010', 9.0, 8.5, 9.0),
('HP002', 'SV00000010', 8.0, 7.5, 8.0),
('HP003', 'SV00000010', 7.5, 8.0, 8.5),
('HP004', 'SV00000010', 8.5, 8.0, 8.5),
('HP005', 'SV00000010', 7.0, 7.5, 7.5),
('HP006', 'SV00000010', 8.0, 8.5, 8.5),
('HP007', 'SV00000010', 7.5, 8.0, 7.5),
('HP008', 'SV00000010', 9.0, 9.0, 9.5),
('HP009', 'SV00000010', 8.5, 8.0, 9.0),
('HP010', 'SV00000010', 8.0, 7.5, 8.0),

('HP001', 'SV00000011', 7.0, 6.5, 7.0),
('HP002', 'SV00000011', 6.5, 7.0, 7.5),
('HP003', 'SV00000011', 7.5, 8.0, 7.5),
('HP004', 'SV00000011', 7.0, 6.5, 7.5),
('HP005', 'SV00000011', 6.0, 5.5, 6.5),
('HP006', 'SV00000011', 7.5, 7.0, 7.0),
('HP007', 'SV00000011', 8.0, 7.5, 7.5),
('HP008', 'SV00000011', 7.5, 8.0, 8.0),
('HP009', 'SV00000011', 6.5, 6.0, 6.5),
('HP010', 'SV00000011', 8.0, 7.5, 8.0),

('HP001', 'SV00000012', 6.5, 7.0, 7.5),
('HP002', 'SV00000012', 7.5, 8.0, 7.0),
('HP003', 'SV00000012', 8.0, 7.5, 8.5),
('HP004', 'SV00000012', 6.0, 5.5, 6.5),
('HP005', 'SV00000012', 7.0, 7.5, 7.0),
('HP006', 'SV00000012', 8.0, 8.5, 8.0),
('HP007', 'SV00000012', 7.5, 7.0, 7.5),
('HP008', 'SV00000012', 7.0, 6.5, 7.5),
('HP009', 'SV00000012', 8.0, 7.5, 8.0),
('HP010', 'SV00000012', 6.5, 7.0, 6.5),

('HP001', 'SV00000013', 9.5, 9.0, 9.5),
('HP002', 'SV00000013', 8.0, 8.5, 9.0),
('HP003', 'SV00000013', 9.0, 8.0, 9.5),
('HP004', 'SV00000013', 7.5, 8.0, 8.0),
('HP005', 'SV00000013', 8.5, 8.0, 9.0),
('HP006', 'SV00000013', 7.0, 7.5, 7.5),
('HP007', 'SV00000013', 9.0, 9.0, 9.5),
('HP008', 'SV00000013', 8.5, 8.0, 8.5),
('HP009', 'SV00000013', 9.0, 8.5, 9.0),
('HP010', 'SV00000013', 8.5, 8.0, 8.5),

('HP001', 'SV00000014', 7.5, 8.0, 7.0),
('HP002', 'SV00000014', 6.5, 7.0, 7.5),
('HP003', 'SV00000014', 8.0, 7.5, 8.0),
('HP004', 'SV00000014', 7.0, 7.5, 7.0),
('HP005', 'SV00000014', 8.5, 8.0, 9.0),
('HP006', 'SV00000014', 7.5, 7.0, 7.5),
('HP007', 'SV00000014', 8.0, 7.5, 8.0),
('HP008', 'SV00000014', 7.5, 8.0, 7.5),
('HP009', 'SV00000014', 7.0, 6.5, 6.5),
('HP010', 'SV00000014', 9.0, 9.5, 9.0),

('HP001', 'SV00000015', 6.5, 7.0, 7.5),
('HP002', 'SV00000015', 7.0, 7.5, 6.5),
('HP003', 'SV00000015', 7.5, 7.0, 7.5),
('HP004', 'SV00000015', 6.5, 6.0, 6.5),
('HP005', 'SV00000015', 8.0, 8.5, 8.5),
('HP006', 'SV00000015', 7.5, 7.0, 7.5),
('HP007', 'SV00000015', 9.0, 8.5, 9.0),
('HP008', 'SV00000015', 8.0, 7.5, 7.5),
('HP009', 'SV00000015', 7.0, 6.5, 6.5),
('HP010', 'SV00000015', 8.5, 8.0, 8.5),

('HP001', 'SV00000016', 7.0, 7.5, 8.0),
('HP002', 'SV00000016', 8.5, 8.0, 8.0),
('HP003', 'SV00000016', 6.5, 6.0, 6.5),
('HP004', 'SV00000016', 8.0, 7.5, 8.5),
('HP005', 'SV00000016', 7.0, 7.5, 7.5),
('HP006', 'SV00000016', 6.0, 6.5, 6.5),
('HP007', 'SV00000016', 9.0, 9.5, 9.0),
('HP008', 'SV00000016', 7.5, 8.0, 8.5),
('HP009', 'SV00000016', 8.5, 8.0, 9.0),
('HP010', 'SV00000016', 8.0, 8.5, 8.5),

('HP001', 'SV00000017', 7.5, 7.0, 7.5),
('HP002', 'SV00000017', 6.5, 6.0, 6.5),
('HP003', 'SV00000017', 8.0, 8.5, 8.0),
('HP004', 'SV00000017', 7.0, 6.5, 7.0),
('HP005', 'SV00000017', 8.5, 8.0, 9.0),
('HP006', 'SV00000017', 6.5, 7.0, 7.0),
('HP007', 'SV00000017', 7.0, 7.5, 7.5),
('HP008', 'SV00000017', 7.5, 8.0, 8.0),
('HP009', 'SV00000017', 8.0, 7.5, 8.0),
('HP010', 'SV00000017', 8.5, 8.0, 8.5),

('HP001', 'SV00000018', 9.0, 8.5, 9.0),
('HP002', 'SV00000018', 8.0, 8.5, 8.5),
('HP003', 'SV00000018', 9.5, 9.0, 9.5),
('HP004', 'SV00000018', 8.5, 8.0, 8.5),
('HP005', 'SV00000018', 7.5, 8.0, 7.5),
('HP006', 'SV00000018', 9.0, 8.5, 9.0),
('HP007', 'SV00000018', 8.5, 8.0, 8.5),
('HP008', 'SV00000018', 7.5, 7.0, 7.5),
('HP009', 'SV00000018', 8.0, 8.5, 8.0),
('HP010', 'SV00000018', 9.0, 9.0, 9.0),

('HP001', 'SV00000019', 6.5, 7.0, 6.5),
('HP002', 'SV00000019', 7.5, 7.0, 7.0),
('HP003', 'SV00000019', 8.0, 8.5, 8.0),
('HP004', 'SV00000019', 7.5, 7.0, 7.5),
('HP005', 'SV00000019', 6.5, 7.0, 6.5),
('HP006', 'SV00000019', 7.0, 7.5, 7.5),
('HP007', 'SV00000019', 7.0, 7.5, 7.0),
('HP008', 'SV00000019', 7.5, 7.0, 7.5),
('HP009', 'SV00000019', 6.5, 6.0, 6.5),
('HP010', 'SV00000019', 7.5, 7.0, 7.5),

('HP001', 'SV00000020', 8.5, 8.0, 8.5),
('HP002', 'SV00000020', 9.0, 8.5, 9.0),
('HP003', 'SV00000020', 7.5, 8.0, 8.0),
('HP004', 'SV00000020', 8.0, 8.5, 8.5),
('HP005', 'SV00000020', 7.0, 7.5, 7.0),
('HP006', 'SV00000020', 8.0, 7.5, 8.0),
('HP007', 'SV00000020', 9.0, 8.5, 9.0),
('HP008', 'SV00000020', 8.5, 8.0, 8.5),
('HP009', 'SV00000020', 8.0, 8.5, 8.0),
('HP010', 'SV00000020', 8.5, 8.0, 8.5);

INSERT INTO DanhGia(noiDung)
VALUES
(N'Hệ thống bị lag'),
(N'Giao diện chưa thân thiện'),
(N'Thời gian phản hồi chậm'),
(N'Chưa có tính năng tìm kiếm nâng cao'),
(N'Mất kết nối khi truy cập quá lâu'),
(N'Thông tin không được cập nhật kịp thời'),
(N'Thuật toán tìm kiếm chưa tối ưu'),
(N'Thông báo lỗi không rõ ràng'),
(N'Khó khăn trong việc thao tác với hệ thống');

INSERT INTO QuanTri(maQT, hoVaTen, matKhau, phanQuyen)
VALUES
('QT001', N'Phạm Việt Long', 'QT@123', 'QT'),
('QT002', N'Nguyễn Hoàng Hải', 'QT@123', 'QT');