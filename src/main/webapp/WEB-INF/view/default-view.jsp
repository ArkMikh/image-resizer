<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<h1>Upload Your File</h1>

    <form action="uploadImage" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    <input type="file" name="downloadedImg" accept="image/*">
                    <input type="submit" value="Upload" multiple>
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
