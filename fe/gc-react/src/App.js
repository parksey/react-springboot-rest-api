import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import {useState} from "react";
import {useEffect} from "react";

import axios from "axios";

import  React from "react";

import ProductList from "./component/ProductList";
import Summary from "./component/Summary";


function App()
{
    const [products, setProducts] = useState([
        {productId:'uuid0-1', productName:'콜롬비아 커피1', category: '커피빈', price: 3000},
        {productId:'uuid0-2', productName:'콜롬비아 커피2', category: '커피빈', price: 3000},
        {productId:'uuid0-3', productName:'콜롬비아 커피3', category: '커피빈', price: 3000}
    ])

    const [items, setItems] = useState([])

    const handleAddClick = productId => {
        const found = items.find(v=>v.productId === productId);
        const product = products.find(value => value.productId === productId);

        const updatedItmes =
            found ? items.map(v=>(v.productId === productId) ? {...v, count: v.count+1} : v) : [...items, {...product, count: 1}]
        setItems(updatedItmes);
    }

    const handleOrderSubmit = (order) => {
        if (items.length === 0) {
            alert("아이템 추가해 주세요")
        } else {
            axios.post("http://localhost:8080/api/v1/orders", {
                email: order.email,
                address: order.address,
                postcode: order.postcode,
                orderItems: items.map(v=> ({
                        productId: v.productId,
                        category: v.category,
                        price: v.price,
                        quantity: v.quantity
                    }))
            }).then(v=>alert("주문 완료"), e=>alert("주문 실패"))
        }
    }

    useEffect(()=> {
        axios.get('http://localhost:8080/api/v1/products')
            .then(v=> setProducts(v.data));
    }, []);

  return (
    <div className="App">
      <div className="container-fluid">
      <div className="row justify-content-center m-4">
        <h1 className="text-center">Grids & Circle</h1>
      </div>
      <div className="card">
        <div className="row">
          <ProductList products={products} onAddClick={handleAddClick}/>
          <Summary items={items} onOrderSubmit={handleOrderSubmit}/>
        </div>
      </div>
      </div>
    </div>
  );
}

export default App;
