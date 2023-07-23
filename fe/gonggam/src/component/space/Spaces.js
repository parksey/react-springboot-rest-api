import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Header from "../Header.js";

import "../../style/Spaces.css";
import { SpacesAllApi } from "../../api/spaces/SpacesApi.js";

export function SpaceSummary({ spaceId, space }) {
  return (
    <>
      <div className="info">
        <div>{space.title}</div>
        <div>인원: {space.capacity}</div>
        <div>금액: {space.amount}</div>
      </div>
    </>
  );
}

function ShareSpace({ isLogin, setIsLogin }) {
  const [spaces, setSpaces] = useState([]);

  console.log(spaces);
  useEffect(() => {
    const fetchData = async () => {
      const spacesList = await SpacesAllApi();
      setSpaces(spacesList);
    };
    fetchData();
  }, []);

  return (
    <>
      <Header isLogin={isLogin} setIsLogin={setIsLogin} />
      <div className="component-container">
        {spaces?.map((space, index) => {
          return (
            <Link
              to={`space/${space.spaceId}`}
              className="component-inner-box Make-component-box"
            >
              <SpaceSummary key={index} space={space} />
            </Link>
          );
        })}
      </div>
    </>
  );
}

export default ShareSpace;
