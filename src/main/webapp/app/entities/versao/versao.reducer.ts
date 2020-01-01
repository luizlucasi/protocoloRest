import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IVersao, defaultValue } from 'app/shared/model/versao.model';

export const ACTION_TYPES = {
  FETCH_VERSAO_LIST: 'versao/FETCH_VERSAO_LIST',
  FETCH_VERSAO: 'versao/FETCH_VERSAO',
  CREATE_VERSAO: 'versao/CREATE_VERSAO',
  UPDATE_VERSAO: 'versao/UPDATE_VERSAO',
  DELETE_VERSAO: 'versao/DELETE_VERSAO',
  RESET: 'versao/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IVersao>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type VersaoState = Readonly<typeof initialState>;

// Reducer

export default (state: VersaoState = initialState, action): VersaoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VERSAO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VERSAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VERSAO):
    case REQUEST(ACTION_TYPES.UPDATE_VERSAO):
    case REQUEST(ACTION_TYPES.DELETE_VERSAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_VERSAO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VERSAO):
    case FAILURE(ACTION_TYPES.CREATE_VERSAO):
    case FAILURE(ACTION_TYPES.UPDATE_VERSAO):
    case FAILURE(ACTION_TYPES.DELETE_VERSAO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_VERSAO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VERSAO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VERSAO):
    case SUCCESS(ACTION_TYPES.UPDATE_VERSAO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VERSAO):
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

const apiUrl = 'api/versaos';

// Actions

export const getEntities: ICrudGetAllAction<IVersao> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_VERSAO_LIST,
  payload: axios.get<IVersao>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IVersao> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VERSAO,
    payload: axios.get<IVersao>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IVersao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VERSAO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVersao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VERSAO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVersao> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VERSAO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
