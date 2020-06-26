<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Material Dash</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/demo/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png" />
</head>
<body>
<script src="${pageContext.request.contextPath}/assets/js/preloader.js"></script>
<div class="body-wrapper">
    <!-- partial:../../partials/_sidebar.jsp -->
    <jsp:include page="../partials/_sidebar.jsp"/>
    <!-- partial -->
    <div class="main-wrapper mdc-drawer-app-content">
        <!-- partial:../../partials/_navbar.jsp -->
        <jsp:include page="../partials/_navbar.jsp"/>
        <!-- partial -->
        <div class="page-wrapper mdc-toolbar-fixed-adjust">
            <main class="content-wrapper">
                <div class="mdc-layout-grid">
                    <div class="mdc-layout-grid__inner">
                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                            <div class="mdc-card p-0">
                                <h6 class="card-title card-padding pb-0">Publisher</h6>
                                <div class="px-5 text-right">
                                    <a href="<s:url namespace="/shop" action="createGet"/>"
                                       class="mdc-button d-inline-flex">
                                        Add new
                                        <i class="material-icons mdc-button__icon">add_box</i>
                                    </a>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th class="text-left">Name</th>
                                            <th>Status</th>
                                            <th>Created At</th>
                                            <th>Updated At</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="shops">
                                            <tr>
                                                <td class="text-left">
                                                    <button class="mdc-button">
                                                        <s:property value="name" />
                                                    </button>
                                                </td>
                                                <td><s:property value="address" /></td>
                                                <td><s:property value="email" /></td>
                                                <td><s:property value="phone" /></td>
                                                <td><s:property value="status" /></td>
                                                <td>
                                                    <a href="<s:url namespace="/shop" action="updateStatusGet">
                                                      <s:param name="id"><s:property value="id" /></s:param>
                                                    </s:url>"
                                                       class="mdc-button mdc-button--raised icon-button filled-button--info px-2">
                                                        <i class="material-icons mdc-button__icon">vignette</i>
                                                    </a>
                                                    <a href="<s:url namespace="/shop" action="updateGet">
                                                      <s:param name="id"><s:property value="id" /></s:param>
                                                    </s:url>"
                                                       class="mdc-button mdc-button--raised icon-button filled-button--success px-2">
                                                        <i class="material-icons mdc-button__icon">edit</i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <!-- partial:../../partials/_footer.jsp -->
            <jsp:include page="../partials/_footer.jsp"/>
            <!-- partial -->
        </div>
    </div>
</div>
<!-- plugins:js -->
<script src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.base.js"></script>
<!-- endinject -->
<!-- Plugin js for this page-->
<!-- End plugin js for this page-->
<!-- inject:js -->
<script src="${pageContext.request.contextPath}/assets/js/material.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/misc.js"></script>
<!-- endinject -->
<!-- Custom js for this page-->
<!-- End custom js for this page-->
</body>
</html>
