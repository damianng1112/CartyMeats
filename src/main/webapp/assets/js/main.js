$(document).ready(function() {
    fetchClients();
    fetchProducts();
    fetchFeedbacks();
    fetchClientsForDropdown();
    fetchProductsForDropdown();
    
    $(document).on('click', '.user-icon', function() {
		console.log("clicked");
        $('#userInfoModal').modal('show');
    });
        
    $('#table_body_client').on('click', '.edit-btn', function () {
		editClientModal(this.parentNode.id);
	});
	
	$(document).on("click", '#btnUpdateClient', function () {
        updateClient();
     });
     
     $('#table_body_client').on('click', '.dlt-btn', function () {
		deleteClient(this.parentNode.id);
	});
	
	$('#product-list').on('click', '.edit-prod-btn', function () {
		editProductModal(this.parentNode.id);
	});
	
	$(document).on("click", '#btnUpdateProduct', function () {
        updateProduct();
     });
	
	$('#product-list').on('click', '.dlt-prod-btn', function () {
		deleteProduct(this.parentNode.id);
	});
	
	// Event listener for the add buyer form submission
	$('#addBuyerForm').submit(function(event) {
	    event.preventDefault(); // Prevent the default form submission
	
	    // Get the selected buyer ID and product ID
	    var buyerId = $('#buyerName').val();
	    var productId = $('#prodName').val();
	
	    // Send an AJAX POST request to the server to associate the buyer with the product
	    $.ajax({
	        type: 'POST',
	        url: 'AddBuyerToProductServlet', // Replace with your servlet endpoint
	        data: { buyerId: buyerId, productId: productId },
	        success: function(response) {
	            // Display a success message or handle the response as needed
	            alert('Buyer added to product successfully');
	            // Render the updated list of products
	            updateAllList();
	            // Clear the form fields after successful submission
	            $('#addBuyerForm')[0].reset();
	        },
	        error: function(xhr, status, error) {
	            console.error(error);
	            alert('Failed to add buyer to product. Please try again later.');
	        }
	    });
	});
	
	// Event listener for the add product form submission
    $('#addProductForm').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Serialize the form data
        var formData = $(this).serialize();

        // Send an AJAX POST request to the server to add the product
        $.ajax({
            type: 'POST',
            url: 'AddProductServlet', // Change this URL to match your servlet endpoint
            data: formData,
            success: function(response) {
                // Display a success message or handle the response as needed
                alert('Product added successfully');
                // Render the updated list of products
                updateAllList();
                // Clear the form fields after successful submission
                $('#addProductForm')[0].reset();
            },
            error: function(xhr, status, error) {
                console.error(error);
                alert('Failed to add product. Please try again later.');
            }
        });
    });
    
    // Event listener for the add client form submission
    $('#AddClient').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission
        
        // Serialize the form data
        var formData = $(this).serialize();
        
        // Send an AJAX POST request to the server
        $.ajax({
            type: 'POST',
            url: 'AddClientServlet',
            data: formData,
            success: function(response) {
				// Display a success message or handle the response as needed
                alert('Client added successfully');
                // Refresh the clients list after adding a new client
                fetchClients();
                
                // Reset the form fields after successful submission
                $('#AddClient')[0].reset();
            },
            error: function(xhr, status, error) {
                // Handle errors if any
                console.error(error);
                alert('Failed to add client. Please try again later.');
            }
        });
    });
});

// Function to open the edit modal with the selected client details
function editClientModal(clientId) {
    console.log('Edit Client ID:', clientId);

    // Make an AJAX request to get the client details by ID
    $.ajax({
        type: 'GET',
    	url: 'FetchClientByIdServlet?id=' + clientId,
        dataType: 'json',
        success: function (data) {
            console.log('Edit Client Details:', data);

            // Populate the edit modal fields with the client details
            $('#editClientId').val(data.id);
            $('#editName').val(data.client_name);
            $('#editAddress').val(data.client_address);
            $('#editFacing').val(data.facing);

            // Show the edit modal
            $('#editModal').modal('show');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Edit Client error: ' + errorThrown);
        }
    });
}

// Function to open the edit modal with the selected product details
function editProductModal(prodId) {
    console.log('Edit Product ID:', prodId);

    // Make an AJAX request to get the product details by ID
    $.ajax({
        type: 'GET',
    	url: 'FetchProdByIdServlet?id=' + prodId,
        dataType: 'json',
        success: function (data) {
            console.log('Edit Product Details:', data);

            // Populate the edit modal fields with the product details
            $('#editProdId').val(data.prodId);
            $('#editProdName').val(data.prodName);
            $('#editProdPic').val(data.prodPic);
            $('#editCategory').val(data.category);
            $('#editDescription').val(data.description);
            $('#editStock').val(data.stock);

            // Show the edit modal
            $('#editModalProduct').modal('show');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Edit Product error: ' + errorThrown);
        }
    });
}

function fetchProducts() {
    $.ajax({
        type: 'GET',
        url: 'FetchProductsServlet',
        dataType: 'json',
        success: function(products) {
            renderProducts(products);
        },
        error: function(xhr, status, error) {
            console.error(error);
            alert('Failed to fetch products. Please try again later.');
        }
    });
}

function updateClient() {
    var updatedClient = {
		"id": $('#editClientId').val(),
        "client_name": $('#editName').val(),
        "client_address": $('#editAddress').val(),
        "facing": $('#editFacing').val(),
        };

    // Make an AJAX request to update the game on the server
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: 'UpdateClientServlet',
        dataType: 'json',
        data: JSON.stringify(updatedClient),
        success: function (data, textStatus, jqXHR) {
            alert('Client updated successfully');
            $('#editModal').modal('hide');
            // Reload the DataTable after updating
            updateAllList();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Update error: ' + errorThrown);
        }
    });
}

function updateProduct() {
    var updatedProduct = {
		"prodId": $('#editProdId').val(),
        "prodName": $('#editProdName').val(),
        "prodPic": $('#editProdPic').val(),
        "category": $('#editCategory').val(),
        "description": $('#editDescription').val(),
        "stock": $('#editStock').val(),
        };

    // Make an AJAX request to update the game on the server
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: 'UpdateProdServlet',
        dataType: 'json',
        data: JSON.stringify(updatedProduct),
        success: function (data, textStatus, jqXHR) {
            alert('Product updated successfully');
            $('#editModalProduct').modal('hide');
            // Reload the DataTable after updating
            updateAllList();
        },
        error: function (jqXHR, textStatus, errorThrown) {
			console.log(updatedProduct);
            alert('Update error: ' + errorThrown);
        }
    });
}

//Calls the DELETE method
var deleteClient = function (clientId) {
    console.log('deleteClient: ' + clientId);
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: 'DeleteClientServlet?id=' + clientId,
        success: function (data, textStatus, jqXHR) {
            alert('Client deleted successfully');
            updateAllList();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteClient error: ' + errorThrown);
        }
    });
};

//Calls the DELETE method
var deleteProduct = function (prodId) {
    console.log('deleteProduct: ' + prodId);
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: 'DeleteProdServlet?id=' + prodId,
        success: function (data, textStatus, jqXHR) {
            alert('Product deleted successfully');
            updateAllList();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteProduct error: ' + errorThrown);
        }
    });
};

var fetchClients=function() {
	console.log('findAllClient');
	$.ajax({
		type: 'GET',
		url: 'FetchClientsServlet',
		dataType: "json", // data type of response
		success: renderClients
	});
};

var fetchFeedbacks=function() {
	console.log('findAllFeedbacks');
	$.ajax({
		type: 'GET',
		url: 'FetchFeedbacksServlet',
		dataType: "json", // data type of response
		success: renderFeedbacks
	});
};

function renderProducts(products) {
    var productListDiv = $('#product-list');

    // Clear existing content
    productListDiv.empty();

    // Loop through the products and create HTML elements to display them
    $.each(products, function(index, product) {
        var productItem = $('<div class="product-item">');
        productItem.append('<p>' + product.prodPic + '</p>');
        productItem.append('<h2>' + product.prodName + '</h2>');
        // Switch case for displaying category
        var categoryText;
        switch (product.category) {
            case 'FREE_RANGE':
                categoryText = 'Free Range';
                break;
            case 'ORGANIC':
                categoryText = 'Organic';
                break;
            case 'PORK_RASHERS':
                categoryText = 'Pork Rashers';
                break;
            case 'TURKEY':
                categoryText = 'Turkey';
                break;
            default:
                categoryText = 'Unknown';
                break;
        }
        productItem.append('<p> Category: ' + categoryText + '</p>');
        productItem.append('<p> Description: ' + product.description + '</p>');
		productItem.append('<p> Stock: ' + product.prodStock + '</p>');
		
        // Check if there are buyers for the product
        if (product.clientNames.length > 0) {
            // If there are buyers, display their names
            productItem.append('<p> Buyers: ' + product.clientNames.join(', ') + '</p>');
        } else {
            // If there are no buyers, display "No buyers" text
            productItem.append('<p> Buyers: No buyers</p>');
        }
        productItem.append('<p id="' + product.prodId + '">' +
            '<button class="btn btn-primary btn-sm edit-prod-btn">Edit</button><br>' +
            '<button class="btn btn-primary btn-sm dlt-prod-btn">Delete</button>' +
            '</p>');

        productListDiv.append(productItem);
    });
}

//updates the DOM to append the returned values and create your list of entries
var renderClients = function(data) {
    // Check if data is not empty
    if (data.length > 0) {
        // Clear existing rows from the table
        $('#clientsTable').DataTable().clear().draw();
        
        // Store all data into a list
        var list = data;
        
        $.each(list, function(index, client) {
            // Render client data onto the webpage
            var row = $('<tr>');
            row.append('<td>' + client.client_name + '</td>');
            row.append('<td>' + client.client_address + '</td>');
            row.append('<td>' + client.facing + '</td>');
            row.append('<td id="' + client.id + '">' +
            '<button class="btn btn-primary btn-sm edit-btn">Edit</button>' +
            '<button class="btn btn-primary btn-sm dlt-btn">Delete</button>' +
            '</td>');
            $('#clientsTable').DataTable().row.add(row).draw();
        });
    } else {
        console.error("No clients data received or invalid format.");
    }
};

var renderFeedbacks = function(data) {
    // Check if data is not empty
    if (data.length > 0) {
        // Clear existing rows from the table
        $('#feedbacksTable').DataTable().clear().draw();
        
        // Store all data into a list
        var list = data;
        console.log(list);
        
        $.each(list, function(index, feedback) {
            // Render client data onto the webpage
            var row = $('<tr>');
            row.append('<td>' + feedback.title + '</td>');
            row.append('<td>' + feedback.description + '</td>');
            row.append('<td>' + feedback.clientName + '</td>');
            row.append('<td>' + feedback.productName + '</td>');
            $('#feedbacksTable').DataTable().row.add(row).draw();
        });
    } else {
        console.error("No feedback data received or invalid format.");
    }
};

// Function to fetch clients and populate the dropdown list
function fetchClientsForDropdown() {
    $.ajax({
        type: 'GET',
        url: 'FetchClientsServlet',
        dataType: 'json',
        success: function(clients) {
            var dropdown = $('#buyerName');
            
            // Clear existing options from the dropdown list
		    dropdown.empty();
		    
		    $.each(clients, function(index, client) {
		        dropdown.append($('<option>').text(client.client_name).attr('value', client.id));
		    });
        },
        error: function(xhr, status, error) {
            console.error(error);
            alert('Failed to fetch clients for dropdown. Please try again later.');
        }
    });
}

// Function to fetch product and populate the dropdown list
function fetchProductsForDropdown() {
    $.ajax({
        type: 'GET',
        url: 'FetchProductsServlet',
        dataType: 'json',
        success: function(products) {
            var dropdown = $('#prodName');
            
            // Clear existing options from the dropdown list
		    dropdown.empty();
		    
		    $.each(products, function(index, product) {
		        dropdown.append($('<option>').text(product.prodName).attr('value', product.prodId));
		    });
        },
        error: function(xhr, status, error) {
            console.error(error);
            alert('Failed to fetch products for dropdown. Please try again later.');
        }
    });
}

function addBuyerToProduct(productId, buyerName) {
    // Make an AJAX request to the server to add the buyer to the product
    $.ajax({
        type: 'POST',
        url: 'AddBuyerToProductServlet', // Replace 'AddBuyerToProductServlet' with the actual URL of your servlet
        data: {
            productId: productId,
            buyerName: buyerName
        },
        success: function(response) {
            // Check if the addition was successful
            if (response.status === 'success') {
                // Reload the product list or update the product with the new buyer
                // You can implement this according to your application's logic
                alert('Buyer added successfully.');
            } else {
                alert('Failed to add buyer.');
            }
        },
        error: function(xhr, status, error) {
            console.error('Error adding buyer:', error);
            alert('Failed to add buyer. Please try again.');
        }
    });
}


// Helper function to update the client list
var updateAllList = function () {
	// Clear the existing list
    $('#table_body').empty(); 
    $('#product-list').empty();
    $('#buyerName').empty();
    $('#prodName').empty();
    // Fetch and render the updated list
    fetchClients(); 
    fetchProducts();
    fetchClientsForDropdown();
    fetchProductsForDropdown();
};
