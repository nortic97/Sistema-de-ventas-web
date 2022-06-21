<?php

use App\Http\Controllers\Roles;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('/roles', [Roles::class, 'index'] );
Route::post('/roles', [Roles::class, 'store']);
Route::get('/roles/{id}', [Roles::class, 'show']);
Route::put('/roles/{id}', [Roles::class, 'update']);
Route::delete('/roles/{id}', [Roles::class, 'destroy']);