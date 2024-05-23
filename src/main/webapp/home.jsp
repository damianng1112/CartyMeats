<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
<link rel="stylesheet" href="assets/css/bootstrap.css"/>
<link rel="stylesheet" href="assets/css/jquery.dataTables.css"/>
<link rel="stylesheet" href="assets/css/styles.css"/>
<link rel="stylesheet" href="assets/css/fontawesome-all.min.css"/>
<script src="assets/js/jquery.js"></script>
<script src="assets/js/jquery.dataTables.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/main.js"></script>
</head>
<body>
    <header>
        <h1>The Carty Cure</h1>
        <div class="user-icon"><i class="fa fa-user"></i></div>
    </header>
    <nav>
        <ul class="nav nav-tabs nav-justified" role="tablist">
            <li class="active"><a href="#feedback" role="tab" data-toggle="tab">Feedback</a></li>
            <li><a href="#products" role="tab" data-toggle="tab">Products</a></li>
            <li><a href="#clients" role="tab" data-toggle="tab">Clients</a></li>
            <li><a href="#price_survey" role="tab" data-toggle="tab">Price Survey</a></li>
        </ul>
    </nav>
    <main>
    	<div class="tab-content">
            <!-- Feedback -->
            <div class="tab-pane active" id="feedback">
                <h2>Feedbacks</h2>
                <!-- Filters for Feedbacks Table -->
				<select id="feedbackClientFilter" class="filter-feedback">
				    <option value="">All Clients</option>
				</select>
				<select id="feedbackProductFilter" class="filter-feedback">
				    <option value="">All Products</option>
				</select>
                <!-- Table to display feedbacks -->
                <table id="feedbacksTable" class="display table table-striped table-bordered">
			        <thead>
			            <tr>
			                <th>Title</th>
			                <th>Description</th>
			                <th>Date</th>
			                <th>Client Name</th>
			                <th>Product Name</th>
			            </tr>
			        </thead>
			        <tbody id="table_body_feedback">
			        
			        </tbody>
			    </table>
            </div>

            <!-- List of Product -->
			<div class="tab-pane" id="products">
			    <div id="product-list" class="row">
			    	<!-- Product items will be appended here -->
			    </div>
			    
			    <!-- Add buyer form -->
				<form id="addBuyerForm">
				    <div class="form-group">
				        <label for="buyerName">Select Buyer:</label>
				        <select class="form-control" id="buyerName" name="buyerName" required>
				            <!-- Options will be populated dynamically -->
				        </select>
				    </div>
					<div class="form-group">
				        <label for="productName">Select Product:</label>
				        <select class="form-control" id="productName" name="productName" required>
				            <!-- Options will be populated dynamically -->
				        </select>
				    </div>				    
				    <button type="submit" class="btn btn-primary">Add Buyer</button>
				</form>
			    
			    <!-- Form to add products -->
				<h3>Add Product</h3>
				<form id="addProductForm">
				    <div class="form-group">
				        <label for="prodName">Product Name:</label>
				        <input type="text" class="form-control" id="prodName" name="prodName" required>
				    </div>
				    <div class="form-group">
				        <label for="prodPic">Product Picture:</label>
				        <input type="text" class="form-control" id="prodPic" name="prodPic" required>
				    </div>
				    <div class="form-group">
				        <label for="category">Category:</label>
				        <select class="form-control" id="category" name="category" required>
				            <option value="free_range">Free Range</option>
				            <option value="organic">Organic</option>
				            <option value="pork_rashers">Pork Rashers</option>
				            <option value="turkey">Turkey</option>
				        </select>
				    </div>
				    <div class="form-group">
				        <label for="description">Description:</label>
				        <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
				    </div>
				    <div class="form-group">
				        <label for="stock">Stock:</label>
				        <input type="number" class="form-control" id="stock" name="stock" required>
				    </div>
				    <div class="form-group">
				        <label for="price">Price:</label>
				        <input type="number" class="form-control" id="price" name="price" required>
				    </div>
				    <button type="submit" class="btn btn-primary">Add Product</button>
				</form>
				<!-- Editing modal product -->
			    <div class="modal fade" id="editModalProduct" role="dialog">
			        <div class="modal-dialog">
			            <div class="modal-content">
			                <div class="modal-header">
			                    <button type="button" class="close" data-dismiss="modal">&times;</button>
			                    <h4 class="modal-title">Edit Product</h4>
			                </div>
			                <div class="modal-body">
			                    <!-- Input fields for editing product properties -->
			                    <input type="hidden" id="editProdId">
			                    <div class="form-group">
			                        <label for="editProdName">Product Name:</label>
			                        <input type="text" class="form-control" id="editProdName">
			                    </div>
			                    <div class="form-group">
			                        <label for="editProdPic">Product Picture:</label>
			                        <input type="text" class="form-control" id="editProdPic">
			                    </div>
			                    <div class="form-group">
							        <label for="editCategory">Category:</label>
							        <select class="form-control" id="editCategory" name="editCategory" required>
							            <option value="free_range">Free Range</option>
							            <option value="organic">Organic</option>
							            <option value="pork_rashers">Pork Rashers</option>
							            <option value="turkey">Turkey</option>
							        </select>
							    </div>
							    <div class="form-group">
							        <label for="editDescription">Description:</label>
							        <textarea class="form-control" id="editDescription" name="editDescription" rows="3" required></textarea>
							    </div>
							    <div class="form-group">
							        <label for="editStock">Stock:</label>
							        <input type="number" class="form-control" id="editStock" name="editStock" required>
							    </div>
							    <div class="form-group">
							        <label for="editPrice">Price:</label>
							        <input type="number" class="form-control" id="editPrice" name="editPrice" required>
							    </div>
			                </div>
			                <div class="modal-footer">
			                    <button type="button" class="btn btn-primary" id="btnUpdateProduct">Save Changes</button>
			                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>

            <!-- Price Survey -->
			<div class="tab-pane" id="price_survey">
                <h2>Price Survey</h2>
                <!-- Filters for Surveys Table -->
				<select id="surveyLocationFilter" class="filter-survey">
				    <option value="">All Locations</option>
				</select>
				<select id="surveyProductFilter" class="filter-survey">
				    <option value="">All Products</option>
				</select>
                <!-- Table for price survey -->
                <table id="surveysTable" class="display table table-striped table-bordered">
			        <thead>
			            <tr>
			                <th>Location</th>
			                <th>Product</th>
			                <th>Price</th>
			                <th>Barcode</th>
			            </tr>
			        </thead>
			        <tbody id="table_body_survey">
			        
			        </tbody>
			    </table>
			    <!-- Comparison Section -->
                <h3>Compare Prices</h3>
                <form id="comparePricesForm">
                    <div class="form-group">
                        <label for="compareProduct1">Product 1:</label>
                        <select class="form-control" id="compareProduct1" name="compareProduct1" required>
                            <!-- Options will be populated dynamically -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="compareProduct2">Product 2:</label>
                        <select class="form-control" id="compareProduct2" name="compareProduct2" required>
                            <!-- Options will be populated dynamically -->
                        </select>
                    </div>
                    <button type="button" class="btn btn-primary" id="comparePricesBtn">Compare</button>
                </form>
                <div id="comparisonResult" class="mt-3">
                    <!-- Comparison results will be displayed here -->
                </div>
			</div>

            <!-- List of Clients -->
            <div class="tab-pane" id="clients">
                <h2>Clients</h2>
                <!-- Table to display clients -->
                <!-- Filters for Clients Table -->
				<select id="clientNameFilter" class="filter-client">
				    <option value="">All Clients</option>
				</select>
				<select id="facingFilter" class="filter-client">
				    <option value="">All Facings</option>
				</select>
				<select id="locationFilter" class="filter-client">
				    <option value="">All Locations</option>
				</select>
                <table id="clientsTable" class="display table table-striped table-bordered">
			        <thead>
			            <tr>
			                <th>Name</th>
			                <th>Location</th>
			                <th>Facing</th>
			                <th>Options</th>
			            </tr>
			        </thead>
			        <tbody id="table_body_client">
			        
			        </tbody>
			    </table>
			    <!-- Form to add clients -->
			    <form id="AddClient">
			        <div class="form-group">
			            <label for="clientName">Name:</label>
			            <input type="text" class="form-control" id="clientName" name="clientName" required>
			        </div>
			        <div class="form-group">
			            <label for="clientAddress">Address:</label>
			            <input type="text" class="form-control" id="clientAddress" name="clientAddress" required>
			        </div>
			        <div class="form-group">
			            <label for="clientFacing">Facing:</label>
			            <input type="text" class="form-control" id="clientFacing" name="clientFacing" required>
			        </div>
			        <button id="addNewClient" type="submit" class="btn btn-primary">Add Client</button>
			    </form>
            </div>
            <!-- Editing modal client -->
		    <div class="modal fade" id="editModal" role="dialog">
		        <div class="modal-dialog">
		            <div class="modal-content">
		                <div class="modal-header">
		                    <button type="button" class="close" data-dismiss="modal">&times;</button>
		                    <h4 class="modal-title">Edit Client</h4>
		                </div>
		                <div class="modal-body">
		                    <!-- Input fields for editing client properties -->
		                    <input type="hidden" id="editClientId">
		                    <div class="form-group">
		                        <label for="editName">Name:</label>
		                        <input type="text" class="form-control" id="editName">
		                    </div>
		                    <div class="form-group">
		                        <label for="editAddress">Address:</label>
		                        <input type="text" class="form-control" id="editAddress">
		                    </div>
		                    <div class="form-group">
		                        <label for="editFacing">Facing:</label>
		                        <input type="text" class="form-control" id="editFacing">
		                    </div>
		                </div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-primary" id="btnUpdateClient">Save Changes</button>
		                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		                </div>
		            </div>
		        </div>
		    </div>
		    <!-- User Info Modal -->
			<div id="userInfoModal" class="modal fade" role="dialog">
				<div class="modal-dialog">
			    	<div class="modal-content">
			            <div class="modal-header">
		                    <button type="button" class="close" data-dismiss="modal">&times;</button>
		                    <h4 class="modal-title">User Info</h4>
		                </div>
		                <div class="modal-body">
					        <div class="profile-card">
							    <div class="profile-picture">
							    	<h4 class="profile-heading">My Profile</h4>	
							        <img src="assets/images/prfImage.jpg" alt="Profile Picture">
							    </div>
							    <div class="profile-details">
							        <p><i class="fa fa-user fa-fw margin-right text-theme"></i>User Name: <c:out value="${sessionScope.employeeInfo.username}"/></p>
							        <hr class="divider">
							        <div class="profile-info">
							            <p><i class="fa fa-envelope fa-fw margin-right text-theme"></i>Email: <c:out value="${sessionScope.employeeInfo.email}"/></p>
							            <p><i class="fa fa-phone fa-fw margin-right text-theme"></i>Contact: <c:out value="${sessionScope.employeeInfo.contact}"/></p>
							        </div>
							    </div>
							</div>
					        <!-- Update User Form -->
					    	<h3>Update User Information</h3>
						    <form action="UpdateUserServlet" method="post">
						    	<input type="hidden" name="prevUsername" value="${sessionScope.employeeInfo.username}">
						        <div class="form-group">
						            <label for="fullname">Full Name:</label>
						            <input type="text" class="form-control" id="fullname" name="fullname" required>
						        </div>
						        <div class="form-group">
						            <label for="userName">User Name:</label>
						            <input type="text" class="form-control" id="userName" name="userName" required>
						        </div>
						        <div class="form-group">
						            <label for="password">Password:</label>
						            <input type="password" class="form-control" id="password" name="password" required>
						        </div>
						        <div class="form-group">
						            <label for="email">Email:</label>	
						            <input type="text" class="form-control" id="email" name="email">
						        </div>
						        <div class="form-group">
						            <label for="contact">Contact:</label>
						            <input type="text" class="form-control" id="contact" name="contact">
						        </div>
						        
						        <input type="submit" name="update" value="Update User Info">
						    </form>
						    
						    <!-- Delete User Option -->
						    <h3>Delete Account</h3>
						    <form action="DeleteAccountServlet" method="post">
						    	<input type="hidden" name="username" value="${sessionScope.employeeInfo.username}">
						        <input type="submit" name="delete" value="Delete Account">
						    </form>
					    </div>
				    </div>
				</div>
			</div>
        </div>
    </main>
    <footer>
        &copy; 2022 The Carty Cure. All rights reserved.
    </footer>
</body>
</html>