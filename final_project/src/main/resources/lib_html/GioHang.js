// Nút checkbox all
function changeCheckboxTitle(checkbox) {
    if (checkbox.checked) {
        checkbox.title = "Đã chọn tất cả sản phẩm";
    } else {
        checkbox.title = "Chọn tất cả sản phẩm";
    }
}

function changeCheckboxTitle(checkbox) {
    // Lấy tất cả các checkbox có class "SelectSP"
    var checkboxes = document.querySelectorAll('.SelectSP');

    // Đặt trạng thái của các checkbox "SelectSP" giống với checkbox "SelectAllSP"
    checkboxes.forEach(function (item) {
        item.checked = checkbox.checked;
    });
}

// Nút checkbox từng sp
function changeCheckboxSP(checkbox) {
    if (checkbox.checked) {
        checkbox.title = "Đã chọn mua sản phẩm";
    } else {
        checkbox.title = "Chọn mua sản phẩm";
    }
}
// Nút tăng giảm số lượng sản phẩm 
document.addEventListener("DOMContentLoaded", function () {
    var increaseBtns = document.querySelectorAll(".PlushC");
    var decreaseBtns = document.querySelectorAll(".PlusT");
    var quantityInputs = document.querySelectorAll(".ValS");
    var thanhTienColumns = document.querySelectorAll(".thanhtienCol");

    // Lặp qua từng sản phẩm và thêm sự kiện cho nút tăng và giảm
    increaseBtns.forEach(function (increaseBtn, index) {
        var decreaseBtn = decreaseBtns[index];
        var quantityInput = quantityInputs[index];
        var thanhTienColumn = thanhTienColumns[index];

        // Chuyển đổi giá trị ban đầu thành số
        var initialPrice = parseFloat(thanhTienColumn.textContent.replace(" vnđ", ""));

        // Thêm sự kiện cho nút tăng
        increaseBtn.addEventListener("click", function () {
            updateQuantity(1);
        });

        // Thêm sự kiện cho nút giảm
        decreaseBtn.addEventListener("click", function () {
            updateQuantity(-1);
        });

        // Thêm sự kiện cho ô nhập liệu
        quantityInput.addEventListener("keypress", function (event) {
            if (event.key === "Enter") {
                updateQuantity(0);
            }
        });

        // Thêm sự kiện cho việc theo dõi sự thay đổi trên ô nhập liệu
        quantityInput.addEventListener("input", function () {
            var currentValue = quantityInput.value.trim();
            if (currentValue === "0") {
                quantityInput.value = "";
            }
            updateQuantity(0);
        });

        // Hàm cập nhật số lượng và tính toán giá trị
        function updateQuantity(change) {
            var currentValue = parseInt(quantityInput.value, 10) + change;
            if (isNaN(currentValue) || currentValue < 0) {
                currentValue = 0;
            }

            quantityInput.value = currentValue;

            var totalPrice = initialPrice * currentValue;
            thanhTienColumn.textContent = formatPrice(totalPrice) + " vnđ";
        }

        // Hàm định dạng giá thành chuỗi với đơn vị là 000
        function formatPrice(price) {
            var formattedPrice = price.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1.");
            return formattedPrice.replace(/\.\d{2}$/, ".000");
        }
    });
});
// Hover cho từng sản phẩm 
document.addEventListener('DOMContentLoaded', function () {
    var productDivs = document.querySelectorAll('.product');

    productDivs.forEach(function (product) {
        product.addEventListener('mouseenter', function () {
            product.style.border = '1px solid #000';
            product.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.2)';
        });

        product.addEventListener('mouseleave', function () {
            product.style.border = ''; // Loại bỏ border
            product.style.boxShadow = '';
        });
    });
});