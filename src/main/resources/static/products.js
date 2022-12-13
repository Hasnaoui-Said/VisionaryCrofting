let List_products = {
    data: {
        content: []
    }
}
const itemSet = (localStorage.getItem('cartProductsRef') === null);
if (!itemSet)  {
    let cartProductsRef = JSON.parse(localStorage.getItem('cartProductsRef'));
    fetchByRef(cartProductsRef.products)
        .then(()=>{
            printData();
        })
        .catch(err=>{
            console.log(err)
        });
}


function printData(){
    let list_shop = document.querySelector(".list-shop");
    let cartProductsRef = JSON.parse(localStorage.getItem('cartProductsRef'));
    list_shop.innerHTML = `
                          <section class="col-4 justify-content-center" style="background-color: #eee;">
                            <div class="container py-5">
                              <div class="row justify-content-center mb-3">
                                <div class="col-md-12 col-xl-10">
                                  <div class="card shadow-0 border rounded-3">
                                    <div class="card-body">
                                      <div class="save row border">
                                        <div class="col-6 offset-3 d-flex flex-column justify-content-center align-content-center ">
                                          <div class="d-flex flex-column mt-4">
                                            <button onclick="saveChange()" class="btn btn-primary btn-sm" type="button">save change</button>
                                          </div>
                                          <div class="d-flex flex-row mt-4 align-items-center mb-1">
                                            <h4 class="mb-1 me-1">$13.99</h4>
                                            <span class="text-danger"><s>$20.99</s></span>
                                          </div>
                                          <h6 class="text-success">Free shipping</h6>
                                          <div class="d-flex flex-column my-4">
                                            <button onclick="confirmCommand()" class="btn btn-outline-primary btn-sm mt-2" type="button">
                                              confirm command
                                            </button>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </section>
                          
                          <section class="col-8 justify-content-center" style="background-color: #eee;">
                            <div class="container py-5">
                              <div class="row justify-content-center mb-3 list-products">
                                
                              </div>
                            </div>
                          </section>
                            `;
    let list_products = document.querySelector(".list-products");
    List_products.data.content.forEach((product,index)=>{
        list_products.innerHTML += `
                                <div class="col-md-12 col-xl-10 mb-2">
                                  <div class="card shadow-0 border rounded-3">
                                    <div class="card-body">
                                      <div class="row">
                                        <div class="col-md-12 col-lg-3 col-xl-3 mb-4 mb-lg-0">
                                          <div class="bg-image hover-zoom ripple rounded ripple-surface">
                                            <img src="${product.url_image}"
                                                 class="w-100" />
                                            <a href="#!">
                                              <div class="hover-overlay">
                                                <div class="mask" style="background-color: rgba(253, 253, 253, 0.15);"></div>
                                              </div>
                                            </a>
                                          </div>
                                        </div>
                                        <div class="col-md-6 col-lg-6 col-xl-6">
                                          <h5>${product.name}</h5>
                                          <div class="d-flex flex-row">
                                            <div class="text-danger mb-1 me-2">
                                              <i class="fa fa-star"></i>
                                              <i class="fa fa-star"></i>
                                              <i class="fa fa-star"></i>
                                              <i class="fa fa-star"></i>
                                            </div>
                                            <span>${product.quantity}</span>
                                          </div>
                                          <div class="mt-1 mb-0 text-muted small">
                                            <span>100% cotton</span>
                                            <span class="text-primary"> • </span>
                                            <span>Light weight</span>
                                            <span class="text-primary"> • </span>
                                            <span>Best finish<br /></span>
                                          </div>
                                          <div class="mb-2 text-muted small">
                                            <span>Unique design</span>
                                            <span class="text-primary"> • </span>
                                            <span>For men</span>
                                            <span class="text-primary"> • </span>
                                            <span>Casual<br /></span>
                                          </div>
                                          <p class="text-truncate mb-4 mb-md-0">
                                            ${product.description}
                                          </p>
                                        </div>
                                        <div class="col-md-6 col-lg-3 col-xl-3 border-sm-start-none border-start">
                                          <div class="d-flex flex-row align-items-center mb-1">
                                            <h4 class="mb-1 me-1">$${product.prix}</h4>
                                            <span class="text-danger"><s>$${eval(product.prix * 1.2)}</s></span>
                                          </div>
                                          <div class="d-flex flex-column mt-4">
                                            <label for="quantity${product.ref}" class="form-label">quantity:</label>
                                            <input type="number" min="1" max="${product.quantity}" 
                                            value="${cartProductsRef.products[index].quantity}" 
                                            class="form-control" id="quantity${product.ref}" name="quantity" placeholder="quantity">
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                            `;
    })
}
async function fetchByRef(products){
    return new Promise((resolve,reject)=>{
        products.forEach((product,index)=>{
                var myHeaders = new Headers();
                myHeaders.append("Content-Type", "application/json");
                var requestOptions = {
                    method: 'GET',
                    headers: myHeaders,
                    redirect: 'follow'
                };

                fetch("http://localhost:9090/api/v1/product/ref/"+product.ref, requestOptions)
                    .then(response => response.text())
                    .then(result => {
                        let resultJson = JSON.parse(result);
                        List_products.data.content.push(resultJson.data);
                        if(index==products.length-1){
                            resolve("data loaded")
                        }
                    }).catch(error => {
                        console.log('error', error)
                        reject(error)
                    });
        });
    })
}
function saveChange(){
    let cartProductsRef = JSON.parse(localStorage.getItem('cartProductsRef'));
    let cartProductsRefUpdate ={products:[]};
    cartProductsRef.products.forEach((product, index)=>{
        let query = "#quantity"+product.ref;
        let input = document.querySelector(query);
        cartProductsRefUpdate.products.push({ref: product.ref, quantity: input.valueAsNumber});
    });

    localStorage.setItem('cartProductsRef', JSON.stringify(cartProductsRefUpdate));
}
function confirmCommand(){
    if (!confirm("Are you sure?! You want to confirm command??")){
        console.log("Not confirmed")
        return ;
    }
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let command = JSON.parse(localStorage.getItem('cartProductsRef'));
    if (document.getElementById("user") == null){
        return alert("Please logon !!!!!............");
    }
    command.client.email = document.getElementById("user").innerText;
    if (command.client.email == null){
        return alert("Please logon !!!!!............");
    }
    var raw = JSON.stringify(command);

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:9090/command/save", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

    localStorage.removeItem('cartProducts');
}