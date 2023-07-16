import React from "react";

import Product from "./Product";

function ProductList({products = [], onAddClick}) {
    return (
        <React.Fragment>
            <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                <h5 className="flex-grow-0"><b>상품 목록</b></h5>
                <ul className="list-group products">
                    {products.map(v=>
                        <li className="list-group-item d-flex mt-3">
                            <Product {...v} onAddClick={onAddClick}/>
                        </li>
                    )}
                </ul>
            </div>
        </React.Fragment>
    )
}

export  default ProductList;