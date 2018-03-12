<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup</title>
<a:bootstrap></a:bootstrap>
</head>
<body>

	<a:navigation></a:navigation>
	<script type="text/javascript">
		$(document).ready(function() {
			var messages = "${messages}";
			if (messages.length > 0) {
				$('#myModal').modal('show');
			}
		});
	</script>

	<form action="SignUpServlet" method="post"
		style="border: 1px solid #ccc">
		<div class="container">
			<h1>Sign Up</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>
			<div class="name">
				<label for="vorname"><b>Vorname</b></label> <input type="text"
					placeholder="Vorname" name="vorname" required size=3> <label
					for="nachname"><b>Nachname</b></label> <input type="text"
					placeholder="Nachname" name="nachname" required size=3> <label
					for="email"><b>Email</b></label> <input type="text"
					placeholder="Email" name="email" required size=3> <label
					for="psw"><b>Password</b></label> <input type="password"
					placeholder="Enter Password" name="psw" required> <label
					for="psw-repeat"><b>Repeat Password</b></label> <input
					type="password" placeholder="Repeat Password" name="psw_repeat"
					required> <label>
			</div>
			<div class="adresse">
				<label for="strasse"><b>Straße</b></label> <input type="text"
					placeholder="Straße" name="strasse" required size=10> <label
					for="hausnummer"><b>Hausnummer</b></label> <input type="text"
					placeholder="Hausnummer" name="hausnummer" required size=3>
				<label for="postleitzahl"><b>Postleitzahl</b></label> <input
					type="text" placeholder="Postleitzahl" name="postleitzahl" required
					size=3> <label for="ort"><b>Ort</b></label> <input
					type="text" placeholder="Ort" name="ort" required size=3>
			</div>
			<div class="pw">
				


				<p>
					By creating an account you agree to our <a href="#"
						style="color: dodgerblue">Terms & Privacy</a>.
				</p>
			</div>

			<div class="clearfix">
				<button type="button" class="cancelbtn">Cancel</button>
				<button type="submit" class="signupbtn">Sign Up</button>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-body">
							<c:forEach items="${messages}" var="item">
    							${item}<br>
							</c:forEach>
						</div>
						<div class="modal-footer">
							<button type="button" class="cancelbtn" data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</form>
</body>
</html>