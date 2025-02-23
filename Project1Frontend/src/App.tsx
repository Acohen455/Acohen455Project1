import './App.css'
import {router} from "./routes";
import {RouterProvider} from "react-router-dom";
import {useEffect, useState} from "react";
import {store} from "./GlobalData/store.ts";

function App() {


  return (
    <>
        <RouterProvider router = {router} />
    </>
  )
}

export default App
