<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>Pixel Admin</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
          <!-- import muc menu chung vào đây -->
       <jsp:include page="menu.jsp"></jsp:include>
        <!-- Left navbar-header end -->
         <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Cập nhật công việc</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                        <p style="color: red">${response}</p>
                            <form action="${pageContext.request.contextPath}/task-update" method="POST" class="form-horizontal form-material">
                                <div class="form-group">
                                    <label class="col-md-12">Dự án</label>
                                    <div class="col-md-12">
                                        <select name="job" class="form-control form-control-line">
                                            <c:forEach items="${listJob}" var="job">
                                            <c:choose>
                                                    <c:when test="${tasksModel.getIdJob() == job.getId()}">
                                                        <option selected value="${job.getId()}">${job.getName()}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${job.getId()}">${job.getName()}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                             </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Tên công việc</label>
                                    <div class="col-md-12">
                                        <input name="nametask" type="text" placeholder="Tên công việc" value="${tasksModel.getName() }"
                                            class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Người thực hiện</label>
                                    <div class="col-md-12">
                                        <select name="user" class="form-control form-control-line">
                                           <c:forEach items="${listUser}" var="user">
                                           <c:choose>
                                                    <c:when test="${tasksModel.getIdUserName() == user.getId()}">
                                                        <option selected value="${user.getId()}">${user.getFirstName()} ${user.getLastName()}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                         <option value="${user.getId()}" >${user.getFirstName()} ${user.getLastName()}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                               
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày bắt đầu</label>
                                    <div class="col-md-12">
                                        <input name="startdate" type="text" placeholder="yyyy-MM-dd" value="${tasksModel.getTaskStartDate() }"
                                            class="form-control form-control-line"> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày kết thúc</label>
                                    <div class="col-md-12">
                                        <input name="enddate" type="text" placeholder="yyyy-MM-dd" value="${tasksModel.getTaskEndDate() }"
                                            class="form-control form-control-line"> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Trạng thái</label>
                                    <div class="col-md-12">
                                        <select name="status" class="form-control form-control-line">
                                           <c:forEach items="${listStatus}" var="status">
                                           <c:choose>
                                                    <c:when test="${tasksModel.getIdStatus() == status.getId()}">
                                                        <option selected value="${status.getId()}">${status.getName()}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${status.getId()}" >${status.getName()}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                               
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                    <input type="hidden" name="id" value="${tasksModel.getId()}">	
                                        <button type="submit" class="btn btn-success">Lưu lại</button>
                                        <a href="${pageContext.request.contextPath}/task" class="btn btn-primary">Quay lại</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 col-12"></div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="js/custom.min.js"></script>
</body>

</html>