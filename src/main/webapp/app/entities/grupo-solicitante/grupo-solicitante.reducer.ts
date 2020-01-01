import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IGrupoSolicitante, defaultValue } from 'app/shared/model/grupo-solicitante.model';

export const ACTION_TYPES = {
  FETCH_GRUPOSOLICITANTE_LIST: 'grupoSolicitante/FETCH_GRUPOSOLICITANTE_LIST',
  FETCH_GRUPOSOLICITANTE: 'grupoSolicitante/FETCH_GRUPOSOLICITANTE',
  CREATE_GRUPOSOLICITANTE: 'grupoSolicitante/CREATE_GRUPOSOLICITANTE',
  UPDATE_GRUPOSOLICITANTE: 'grupoSolicitante/UPDATE_GRUPOSOLICITANTE',
  DELETE_GRUPOSOLICITANTE: 'grupoSolicitante/DELETE_GRUPOSOLICITANTE',
  RESET: 'grupoSolicitante/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IGrupoSolicitante>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type GrupoSolicitanteState = Readonly<typeof initialState>;

// Reducer

export default (state: GrupoSolicitanteState = initialState, action): GrupoSolicitanteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_GRUPOSOLICITANTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_GRUPOSOLICITANTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_GRUPOSOLICITANTE):
    case REQUEST(ACTION_TYPES.UPDATE_GRUPOSOLICITANTE):
    case REQUEST(ACTION_TYPES.DELETE_GRUPOSOLICITANTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_GRUPOSOLICITANTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_GRUPOSOLICITANTE):
    case FAILURE(ACTION_TYPES.CREATE_GRUPOSOLICITANTE):
    case FAILURE(ACTION_TYPES.UPDATE_GRUPOSOLICITANTE):
    case FAILURE(ACTION_TYPES.DELETE_GRUPOSOLICITANTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_GRUPOSOLICITANTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_GRUPOSOLICITANTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_GRUPOSOLICITANTE):
    case SUCCESS(ACTION_TYPES.UPDATE_GRUPOSOLICITANTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_GRUPOSOLICITANTE):
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

const apiUrl = 'api/grupo-solicitantes';

// Actions

export const getEntities: ICrudGetAllAction<IGrupoSolicitante> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_GRUPOSOLICITANTE_LIST,
  payload: axios.get<IGrupoSolicitante>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IGrupoSolicitante> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_GRUPOSOLICITANTE,
    payload: axios.get<IGrupoSolicitante>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IGrupoSolicitante> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_GRUPOSOLICITANTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IGrupoSolicitante> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_GRUPOSOLICITANTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IGrupoSolicitante> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_GRUPOSOLICITANTE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
