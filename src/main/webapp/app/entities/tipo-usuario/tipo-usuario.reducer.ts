import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITipoUsuario, defaultValue } from 'app/shared/model/tipo-usuario.model';

export const ACTION_TYPES = {
  FETCH_TIPOUSUARIO_LIST: 'tipoUsuario/FETCH_TIPOUSUARIO_LIST',
  FETCH_TIPOUSUARIO: 'tipoUsuario/FETCH_TIPOUSUARIO',
  CREATE_TIPOUSUARIO: 'tipoUsuario/CREATE_TIPOUSUARIO',
  UPDATE_TIPOUSUARIO: 'tipoUsuario/UPDATE_TIPOUSUARIO',
  DELETE_TIPOUSUARIO: 'tipoUsuario/DELETE_TIPOUSUARIO',
  RESET: 'tipoUsuario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITipoUsuario>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TipoUsuarioState = Readonly<typeof initialState>;

// Reducer

export default (state: TipoUsuarioState = initialState, action): TipoUsuarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TIPOUSUARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TIPOUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TIPOUSUARIO):
    case REQUEST(ACTION_TYPES.UPDATE_TIPOUSUARIO):
    case REQUEST(ACTION_TYPES.DELETE_TIPOUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TIPOUSUARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TIPOUSUARIO):
    case FAILURE(ACTION_TYPES.CREATE_TIPOUSUARIO):
    case FAILURE(ACTION_TYPES.UPDATE_TIPOUSUARIO):
    case FAILURE(ACTION_TYPES.DELETE_TIPOUSUARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPOUSUARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPOUSUARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TIPOUSUARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_TIPOUSUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TIPOUSUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/tipo-usuarios';

// Actions

export const getEntities: ICrudGetAllAction<ITipoUsuario> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TIPOUSUARIO_LIST,
  payload: axios.get<ITipoUsuario>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITipoUsuario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TIPOUSUARIO,
    payload: axios.get<ITipoUsuario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITipoUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TIPOUSUARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITipoUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TIPOUSUARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITipoUsuario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TIPOUSUARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
