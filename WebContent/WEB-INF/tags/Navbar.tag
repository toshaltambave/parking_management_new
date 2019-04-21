    <!-- Navigation -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
      <a class="navbar-brand" href="#">University Of Texas At Arlington</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" id="homebutton" href="${pageContext.request.contextPath}/HomeController">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Welcome, ${User.username}</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">${User.role}</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>