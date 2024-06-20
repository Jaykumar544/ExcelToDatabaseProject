<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Excel File</title>
</head>
<body>
    <h2>Upload Excel File</h2>
    <br>
    <h3 style="color:green;">${successMsg}</h3>
    <h3 style="color:red;">${failedMsg}</h3>

    <form action="/setExcel" method="post" enctype="multipart/form-data">
        <input type="file" name="file" accept=".xls,.xlsx" required>
        <button type="submit">Upload</button>
    </form>

    <br><br><br><br>
    <b>
    <a href="/report/pdf"> Generate report in pdf format </a> <br>
    <a href="/report/html"> Generate report in html format </a> <br>
    <a href="/report/csv"> Generate report in csv format </a> <br>
    <a href="/report/xlsx"> Generate report in xlsx format </a> <br>
    </b>
</body>
</html