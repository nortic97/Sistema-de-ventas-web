<?php

namespace App\Http\Controllers;

use App\Models\Users as UsersModel;
use Illuminate\Http\Request;

use function PHPUnit\Framework\isEmpty;

class Users extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

        $user = UsersModel::paginate(15)->load('roles');

        if(empty($user)){

            $json = array(

                'status' => 'Error',
                'code' => 404,
                'message' => 'La base de datos esta vacia'

            );

        }else{

             $json = array(

                'status' => 'succes',
                'code' => 200,
                'usuario' => $user

            );

        }

        return response()->json($json, $json['code']);

    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
