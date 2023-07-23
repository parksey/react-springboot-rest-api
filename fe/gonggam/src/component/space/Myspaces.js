import { useEffect, useState } from "react";
import "../../style/Spaces.css";
import Header from "../Header";
import { Link } from "react-router-dom";
import { SpaceSummary } from "./Spaces";
import { MySpacesApi } from "../../api/spaces/SpacesApi";

function Myspaces({ isLogin, setIsLogin }) {
  const [spaces, setSpaces] = useState([]);

  console.log(spaces);
  useEffect(() => {
    const fetchData = async () => {
      const spacesList = await MySpacesApi();
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

export default Myspaces;
