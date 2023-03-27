import React, { useState } from "react";
import { useForm, useController, Controller } from "react-hook-form";
import './style.css'
import Select from 'react-select';
import {IoAddOutline} from "react-icons/io5";

import dataSevice from "./dataSevice";

const options = [
  { value: "POST", label: "POST" },
  { value: "GET", label: "GET" },
  { value: "PUT", label: "PUT" },
  { value: "DELETE", label: "DELETE" }
];
const list =[
  {value:"String",label:"String"},
  {value:"Number",label:"Number"},
  {value:"Boolean",label:"Boolean"},
  {value:"Object",label:"Object"},
  {value:"Array",label:"Array"},
  {value:"Date",label:"Date"}
]


function App() {
  const {
    register,
    handleSubmit,
    control,
    getValues
  } = useForm();

  const { field : { value : typeValue, onChange: typeOnChange, resTypeField } } = useController( { name : "reqType", control });

  const [dataSchema, setDataSchema] = useState([{key:"", value:""}]);

  const handleClick = () => {
    setDataSchema([...dataSchema, {key:"", value:""}]);
  }

  const handleChange = (e,i) => {
    const {name, value} = e.target
    const onChangeVal = [...dataSchema]
    onChangeVal[i][name] = value
    setDataSchema(onChangeVal);
  }

  const handleDelete = (i) => {
    const deleteVal = [...dataSchema];
    deleteVal.splice(i,1);
    setDataSchema(deleteVal);
  }

  const onSubmit = () => {
    console.log(getValues(["mockname", "reqType"]));
    console.log(getValues("url"));
    console.log(dataSchema.map(({key, value}) => `"${key}":"${value}"`).join(','));
    console.log(getValues(["rid", "response"]));

    dataSevice.saveMockAPI(getValues("mockname"), JSON.parse("{" + dataSchema.map(({key, value}) => `"${key}":"${value}"`).join(',') + "}"), {"url" : getValues("url"), "operation" : getValues("reqType"), "response" : {"rid" : getValues("rid"), "response" : JSON.parse(getValues("response"))}}).then(
      (response) => {
        console.log(response);
      }
    );

  };


  return (
    <div className="App">

      <h1>MOCK API</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="input-container">
          <label>MockName</label>
          <input className="input-text" type="text" name="mockname" {...register("mockname")}/>
        </div>

        <div className="select-container">
          <label>Select request type : </label>
          <Select
            className='dropdown-contianer'
            placeholder="Select Request Type"
            isClearable
            options={options}
            value={typeValue ? options.find(x => x.value === typeValue) : typeValue}
            onChange={option => typeOnChange(option ? option.value : option)}
            {...resTypeField}
          />
        </div>

        <div className="input-container">
          <label>Endpoint URL</label>
          <input className="input-text" type="text" name="url" {...register("url")}/>
        </div>

        <div className='test-container'>
            <span>Data Schema:</span>
            <button className='btn-container' onClick={handleClick}><IoAddOutline className='add-icon'/></button>
            {
                dataSchema.map((val,i)=>
                <div className='text-container'>
                    <input className='input-box' name="key" value={val.key} onChange={(e)=>handleChange(e,i)} />
                    <input className='input-box' name="value" value={val.value} onChange={(e)=>handleChange(e,i)} />
                    <button  className='btn'onClick={()=>handleDelete(i)}>Delete</button>
                </div>
                )
            }
           
        </div>

        <div className="input-container">
          <label>Response ID</label>
          <input className="input-text" type="number" name="rid" {...register("rid")}/>
        </div>

        <label>Response Body:</label>
        <div className="text-container">  
          <textarea  className ="text-area" rows={20} name="response" {...register("response")}/>
        </div>

        <button className="btn-modal" type = "submit">Submit</button>
      </form>
    </div>
  )
}

export default App;