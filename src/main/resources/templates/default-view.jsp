<!DOCTYPE html>
<html>
<body>

    <h1>Upload Your File</h1>


    <form action="uploadImage" enctype="multipart/form-data" method="post" >

        <input type="file" name="downloadedImg" multiple accept="image/jpeg,image/png,image/bmp,image/gif">

        <br><br>

        <label>

            <input type="text" name="imageScaledWidth" placeholder="Width">
            <input type="text" name="imageScaledHeight" placeholder="Height">

            <select name="extension">
                <option disabled>Choose extension</option>
                <option value="png">PNG</option>
                <option value="jpg">JPG</option>
                <option value="bmp">BMP</option>
                <option value="gif">GIF</option>
            </select>

            <br><br>

            <input type="submit" value="Upload" multiple>

        </label>

    </form>


</body>
</html>
