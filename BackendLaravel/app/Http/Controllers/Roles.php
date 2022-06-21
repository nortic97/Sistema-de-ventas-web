<?php

namespace App\Http\Controllers;

use App\Models\Roles as RolesModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class Roles extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    public function index()
    {

        $roles = RolesModel::paginate(20);

        if ($roles->isEmpty()) {

            $json = array(

                'status' => 'Error',
                'code' => 404,
                'message' => 'Base de datos vacia'

            );
        } else {

            $json = array(

                'role' => $roles,
                'code' => 200

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

        $jsonData = $request->input('role', null);

        $params = json_decode($jsonData, true);

        $params['role_name'] = strtoupper($params['role_name']); //Nombre del rol siempre en mayusculas.

        $params = array_map('trim', $params);

        $rules = [

            'role_name' => 'required|unique:roles|alpha'

        ];

        $messages = [

            'required' => 'El campo :attribute es requerido',
            'unique' => 'El campo :attribute ya existe',
            'alpha' => 'El campo :attribute solo acepta letras'

        ];

        $validate = Validator::make($params, $rules, $messages);


        if ($validate->fails()) {

            $json = array(

                'status' => 'Error',
                'code' => 400,
                'message' => $validate->errors()

            );
        } else {

            $role = new RolesModel();

            $role->role_name = $params['role_name'];

            $role->save();

            $json = array(

                'status' => 'Success',
                'code' => 201,
                'message' => 'Rol ' . $role->role_name . ' guardado.'

            );
        }


        return response()->json($json, $json['code']);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {

        $role = RolesModel::find($id);

        if (is_null($role)) {

            $json = array(

                'status' => 'Error',
                'code' => 404,
                'message' => 'el dato no existe',
                'search' => $id

            );
        } else {

            $json = array(

                'status' => 'success',
                'code' => 200,
                'search' => $role

            );
        }

        return response()->json($json, $json['code']);
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

        $role = RolesModel::find($id);

        if (is_null($role)) {

            $json = array(

                'status' => 'Error',
                'code' => 404,
                'message' => 'el dato no existe',
                'search' => $id

            );
        } else {

            $jsonData = $request->input('role', null);

            $params = json_decode($jsonData, true);

            $params['role_name'] = strtoupper($params['role_name']); //Nombre del rol siempre en mayusculas.

            $params = array_map('trim', $params);

            if (!empty($params)) {

                $rules = [

                    'role_name' => 'required|unique:roles|alpha'

                ];

                $messages = [

                    'required' => 'El campo :attribute es requerido',
                    'unique' => 'El campo :attribute ya existe',
                    'alpha' => 'El campo :attribute solo acepta letras'

                ];

                $validate = Validator::make($params, $rules, $messages);


                if ($validate->fails()) {

                    $json = array(

                        'status' => 'Error',
                        'code' => 400,
                        'message' => $validate->errors()

                    );
                } else {

                    unset($params['id']);

                    RolesModel::where('id', $id)->update($params);

                    $json = array(

                        'status' => 'Success',
                        'code' => 200,
                        'message' => 'Rol ' . $role->role_name . ' actualizado.'

                    );
                }
            }
        }

        return response()->json($json, $json['code']);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {

        $role = RolesModel::find($id);

        if (!is_null($role)) {

            $role->delete();

            $json = array(

                'status' => 'success',
                'code' => 200,
                'message' => 'Se ha eliminado el rol.',
                'search' => $role

            );
        } else {

            $json = array(

                'status' => 'error',
                'code' => 404,
                'message' => 'No existe el rol.',
                'search' => $id

            );
        }

        return response()->json($json, $json['code']);
    }
}
