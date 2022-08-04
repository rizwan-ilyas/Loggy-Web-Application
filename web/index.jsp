
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <title>My Page</title>
    </head>

    <body>
        <div class="container mt-5" style="width: 650px;">
            <h1>Log Writing</h1>
            
            <form class="form" action="LogServlet" method="POST">
                
                <div class="mb-4">
                    <label for="log" class="form-label">Log Title</label>
                    <input type="text" class="form-control" id="log" name="title">
                    
                </div>
                <div class="mb-4">
                    <label class="form-label">Log Content</label>
                    <textarea class="form-control" rows="7" id="content" name="content"></textarea>
                </div>

                <button type="submit" class="btn btn-primary">Create Log</button>
                
                
            </form>


        </div>

    </body>
</html>
