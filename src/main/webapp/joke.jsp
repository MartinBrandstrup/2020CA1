<!DOCTYPE html>
<html>
    <head>
        <title>Cars</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/CarCSS.css" rel="stylesheet">

    </head>
    <body>

        <!-- Page Content -->
        <section class="py-5">
            <div class="container">
<!--                <form>
                    Car id#: <input type="text" id="carInput">
                    <input type="submit" id="showSingleButton" value="Pick a car by id#">
                </form>-->
                <input type="submit" id="buttonAll" value="Show a table with all cars">
                <input type="submit" id="buttonSortPrice" value="Sort the table by price">
                <input type="submit" id="buttonSortYear" value="Sort the table by year">
                <input type="submit" id="buttonSortMake" value="Sort the table by make">

                <div id="carTable"></div>
            </div>
        </section>
        
        <p><b><a href="index.html" 
                 target="/2020CA1/">Return to index</a></b></p>

        <script src="JavaScript/CarJS.js"></script>
    </body>
</html>