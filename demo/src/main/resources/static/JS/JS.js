function toggleMenu() {
    var navigationLinks = document.getElementById("navigationLinks");
    var rightLinks = document.getElementById("rightLinks");

    if (navigationLinks.classList.contains("show")) {
        navigationLinks.classList.remove("show");
        rightLinks.classList.remove("show");
    } else {
        navigationLinks.classList.add("show");
        rightLinks.classList.add("show");
    }
}

$(document).ready(function () {
    // Send Search Text to the server
    $("#search").keyup(function () {
      let searchText = $(this).val();
      if (searchText != "") {
        $.ajax({
          url: "./action.php",
          method: "post",
          data: {
            query: searchText,
          },
          success: function (response) {
            $("#show-list").html(response);
          },
        });
      } else {
        $("#show-list").html("");
      }
    });
    // Set searched text in input field on click of search button
    $(document).on("click", "a", function () {
      $("#search").val($(this).text());
      $("#show-list").html("");
    });
  });

  function sendproduct(inputElement) {
    const prodsearch = document.getElementById("prodsearch");
    const searchTerm = inputElement.value.trim();

    if (searchTerm.length === 0) {
        prodsearch.innerHTML = '';
        return;
    }

    fetch(`/search?term=${encodeURIComponent(searchTerm)}`)
        .then(res => res.json())
        .then(data => {
            prodsearch.innerHTML = '';
            if (data.length < 1) {
                prodsearch.innerHTML = '<p>Sorry, nothing found.</p>';
                return;
            }
            data.forEach((product) => {
                const productLink = document.createElement('a');
                productLink.href = `/productDetails/${product.id}`;
                productLink.innerHTML = `<p>${product.name} - ${product.price}</p>`;
                prodsearch.appendChild(productLink);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            prodsearch.innerHTML = '<p>Error fetching results.</p>';
        });
}